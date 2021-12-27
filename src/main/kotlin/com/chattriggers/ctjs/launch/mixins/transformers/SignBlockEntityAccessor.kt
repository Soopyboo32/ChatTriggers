package com.chattriggers.ctjs.launch.mixins.transformers

import net.minecraft.block.entity.SignBlockEntity
import net.minecraft.text.Text
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.gen.Invoker

@Mixin(SignBlockEntity::class)
interface SignBlockEntityAccessor {
    @Invoker
    fun getTexts(filtered: Boolean): Array<Text>
}
