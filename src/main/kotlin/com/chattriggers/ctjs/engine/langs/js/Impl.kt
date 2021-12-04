package com.chattriggers.ctjs.engine.langs.js

import com.chattriggers.ctjs.engine.ILoader
import com.chattriggers.ctjs.engine.IRegister
import com.chattriggers.ctjs.minecraft.objects.display.Display
import com.chattriggers.ctjs.minecraft.objects.display.DisplayLine
import com.chattriggers.ctjs.minecraft.objects.gui.Gui
import com.chattriggers.ctjs.triggers.OnTrigger
import org.graalvm.polyglot.Value
import kotlin.reflect.full.memberFunctions

/*
 * This file holds the "glue" for this language.
 *
 * Certain classes have triggers inside them that need to know what loader to use,
 * and that's where these implementations come in.
 */

object JSRegister : IRegister<Value> {
    override fun register(triggerType: String, method: Value): OnTrigger {
        val name = triggerType.lowercase()

        var func = IRegister.methodMap[name]

        if (func == null) {
            func = this::class.memberFunctions.firstOrNull {
                it.name.lowercase() == "register$name"
            } ?: throw NoSuchMethodException("No trigger type named '$triggerType'")
            IRegister.methodMap[name] = func
        }

        return func.call(this, method) as OnTrigger
    }
    override fun getImplementationLoader(): ILoader = JSLoader
}

class JSGui : Gui() {
    override fun getLoader(): ILoader = JSLoader
}

class JSDisplayLine : DisplayLine {
    constructor(text: String) : super(text)
    constructor(text: String, config: Value) : super(text, config)

    override fun getLoader(): ILoader = JSLoader
}

class JSDisplay : Display {
    constructor() : super()
    constructor(config: Value) : super(config)

    override fun createDisplayLine(text: String): DisplayLine {
        return JSDisplayLine(text)
    }
}
