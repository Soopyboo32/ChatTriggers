package com.chattriggers.ctjs.engine.module

import com.chattriggers.ctjs.CTJS
import com.chattriggers.ctjs.Reference
import com.chattriggers.ctjs.engine.loader.ModuleUpdater
import com.chattriggers.ctjs.engine.loader.ILoader
import com.chattriggers.ctjs.minecraft.libs.FileLib
import com.chattriggers.ctjs.print
import com.chattriggers.ctjs.triggers.TriggerType
import com.chattriggers.ctjs.utils.config.Config
import com.chattriggers.ctjs.utils.console.Console
import org.apache.commons.io.FileUtils
import java.io.File

object ModuleManager {
    val loaders = mutableListOf<ILoader>()
    val generalConsole = Console(null)
    val cachedModules = mutableListOf<Module>()
    val modulesFolder = File(Config.modulesFolder)

    fun setup() {
        modulesFolder.mkdirs()

        // Download pending modules
        ModuleUpdater.importPendingModules()

        // Get existing modules
        val installedModules = getFoldersInDir(modulesFolder).map {
            parseModule(it)
        }.distinctBy {
            it.name.toLowerCase()
        }

        // Check if those modules have updates
        installedModules.forEach(ModuleUpdater::updateModule)
        cachedModules.addAll(installedModules)

        // Import required modules
        installedModules.asSequence().mapNotNull {
            it.metadata.requires
        }.flatten().distinct().forEach {
            ModuleUpdater.importModule(it)
        }

        // Load their assets
        loadAssets(cachedModules)

        // Normalize all metadata
        cachedModules.forEach {
            it.metadata.entry = it.metadata.entry?.replace('/', File.separatorChar)?.replace('\\', File.separatorChar)
        }

        // Get all jars
        val jars = cachedModules.map {
            it.folder.listFiles()?.toList() ?: listOf()
        }.flatten().filter {
            it.name.endsWith(".jar")
        }.map {
            it.toURI().toURL()
        }

        // Setup all loaders
        loaders.forEach {
            it.setup(jars)
        }
    }

    private fun getFoldersInDir(dir: File): List<File> {
        if (!dir.isDirectory) return emptyList()

        return dir.listFiles()?.filter {
            it.isDirectory
        } ?: listOf()
    }

    fun parseModule(directory: File): Module {
        val metadataFile = File(directory, "metadata.json")
        var metadata = ModuleMetadata()

        if (metadataFile.exists()) {
            try {
                metadata = ModuleUpdater.gson.fromJson(FileLib.read(metadataFile), ModuleMetadata::class.java)
            } catch (exception: Exception) {
                exception.print()
            }
        }

        return Module(directory.name, metadata, directory)
    }

    fun entryPass(modules: List<Module> = cachedModules) {
        // Load the modules
        loaders.forEach { loader ->
            modules.filter {
                File(it.folder, it.metadata.entry ?: return@filter false).extension == loader.getLanguage().extension
            }.forEach {
                loader.entryPass(it, File(it.folder, it.metadata.entry!!).toURI())
            }
        }
    }

    fun importModule(moduleName: String): Module? {
        val newModules = ModuleUpdater.importModule(moduleName)

        // Load their assets
        loadAssets(newModules)

        // Normalize all metadata
        newModules.forEach {
            it.metadata.entry = it.metadata.entry?.replace('/', File.separatorChar)?.replace('\\', File.separatorChar)
        }

        // TODO: Print warning to console if metadatas contain an asm key

        entryPass(newModules)

        return newModules.getOrNull(0)
    }

    fun deleteModule(name: String): Boolean {
        if (FileLib.deleteDirectory(File(modulesFolder, name))) {
            Reference.loadCT()
            return true
        }
        return false
    }

    private fun loadAssets(modules: List<Module>) {
        modules.map {
            File(it.folder, "assets")
        }.filter {
            it.exists() && !it.isFile
        }.map {
            it.listFiles()?.toList() ?: emptyList()
        }.flatten().forEach {
            FileUtils.copyFileToDirectory(it, CTJS.assetsDir)
        }
    }

    fun teardown() {
        cachedModules.clear()

        loaders.forEach {
            it.clearTriggers()

            if (Config.clearConsoleOnLoad) {
                it.console.clearConsole()
            }
        }

        if (Config.clearConsoleOnLoad)
            generalConsole.clearConsole()
    }

    fun trigger(type: TriggerType, arguments: Array<out Any?>) {
        loaders.forEach {
            it.exec(type, arguments)
        }
    }

    fun getConsole(language: String): Console {
        return loaders.firstOrNull {
            it.getLanguage().langName == language
        }?.console ?: generalConsole
    }
}