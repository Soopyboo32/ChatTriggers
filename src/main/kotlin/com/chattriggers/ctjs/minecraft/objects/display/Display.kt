package com.chattriggers.ctjs.minecraft.objects.display

import com.chattriggers.ctjs.utils.kotlin.External
import com.chattriggers.ctjs.utils.kotlin.NotAbstract
import com.oracle.truffle.js.runtime.JSRuntime
import org.graalvm.polyglot.Value

@External
@NotAbstract
abstract class Display {
    private var lines = mutableListOf<DisplayLine>()

    private var renderX = 0f
    private var renderY = 0f
    private var shouldRender = true

    private var backgroundColor: Long = 0x50000000
    private var textColor: Long = 0xffffffff

    private var background = DisplayHandler.Background.NONE
    private var align = DisplayHandler.Align.LEFT
    private var order = DisplayHandler.Order.DOWN

    private var minWidth = 0f
    private var width = 0f
    private var height = 0f

    constructor() {
        DisplayHandler.registerDisplay(this)
    }

    constructor(config: Value) {
        shouldRender = config.getMember("shouldRender")?.let(JSRuntime::toBoolean) ?: true
        renderX = config.getMember("renderX")?.let(JSRuntime::toNumber)?.toFloat() ?: 0f
        renderY = config.getMember("renderY")?.let(JSRuntime::toNumber)?.toFloat() ?: 0f

        backgroundColor = config.getMember("backgroundColor")?.let(JSRuntime::toNumber)?.toLong() ?: 0x50000000
        textColor = config.getMember("textColor")?.let(JSRuntime::toNumber)?.toLong() ?: 0xffffffff

        minWidth = config.getMember("minWidth")?.let(JSRuntime::toNumber)?.toFloat() ?: 0f

        setBackground(config.getMember("background") ?: DisplayHandler.Background.NONE)
        setAlign(config.getMember("align") ?: DisplayHandler.Align.LEFT)
        setOrder(config.getMember("order") ?: DisplayHandler.Order.DOWN)

        DisplayHandler.registerDisplay(this)
    }

    fun getBackgroundColor(): Long = backgroundColor

    fun setBackgroundColor(backgroundColor: Long) = apply {
        this.backgroundColor = backgroundColor
    }

    fun getTextColor(): Long = textColor

    fun setTextColor(textColor: Long) = apply {
        this.textColor = textColor
    }

    fun getBackground(): DisplayHandler.Background = background

    fun setBackground(background: Any) = apply {
        this.background = when (background) {
            is String -> DisplayHandler.Background.valueOf(background.uppercase().replace(" ", "_"))
            is DisplayHandler.Background -> background
            else -> DisplayHandler.Background.NONE
        }
    }

    fun getAlign(): DisplayHandler.Align = align

    fun setAlign(align: Any) = apply {
        this.align = when (align) {
            is String -> DisplayHandler.Align.valueOf(align.uppercase())
            is DisplayHandler.Align -> align
            else -> DisplayHandler.Align.LEFT
        }
    }

    fun getOrder(): DisplayHandler.Order = order

    fun setOrder(order: Any) = apply {
        this.order = when (order) {
            is String -> DisplayHandler.Order.valueOf(order.uppercase())
            is DisplayHandler.Order -> order
            else -> DisplayHandler.Order.DOWN
        }
    }

    fun setLine(index: Int, line: Any) = apply {
        while (lines.size - 1 < index) lines.add(createDisplayLine(""))
        lines[index] = when (line) {
            is String -> createDisplayLine(line)
            is DisplayLine -> line
            else -> createDisplayLine("")
        }
    }

    fun getLine(index: Int): DisplayLine = lines[index]

    fun getLines(): List<DisplayLine> = lines

    fun setLines(lines: MutableList<DisplayLine>) = apply {
        this.lines = lines
    }

    @JvmOverloads
    fun addLine(index: Int = -1, line: Any) {
        val toAdd = when (line) {
            is String -> createDisplayLine(line)
            is DisplayLine -> line
            else -> createDisplayLine("")
        }

        if (index == -1) lines.add(toAdd)
        else lines.add(index, toAdd)
    }

    fun addLines(vararg lines: Any) = apply {
        lines.forEach {
            this.lines.add(
                when (it) {
                    is String -> createDisplayLine(it)
                    is DisplayLine -> it
                    else -> createDisplayLine("")
                }
            )
        }
    }

    fun clearLines() = apply {
        lines.clear()
    }

    fun getRenderX(): Float = renderX

    fun setRenderX(renderX: Float) = apply {
        this.renderX = renderX
    }

    fun getRenderY(): Float = renderY

    fun setRenderY(renderY: Float) = apply {
        this.renderY = renderY
    }

    fun setRenderLoc(renderX: Float, renderY: Float) = apply {
        this.renderX = renderX
        this.renderY = renderY
    }

    fun getShouldRender(): Boolean = shouldRender

    fun setShouldRender(shouldRender: Boolean) = apply {
        this.shouldRender = shouldRender
    }

    fun getWidth(): Float = width

    fun getHeight(): Float = height

    fun getMinWidth(): Float = minWidth

    fun setMinWidth(minWidth: Float) = apply {
        this.minWidth = minWidth
    }

    fun render() {
        if (!shouldRender) return

        var maxWidth = minWidth
        lines.forEach {
            if (it.getTextWidth() > maxWidth)
                maxWidth = it.getTextWidth()
        }

        width = maxWidth

        var i = 0f
        lines.forEach {
            drawLine(it, renderX, renderY + (i * 10), maxWidth)
            when (order) {
                DisplayHandler.Order.DOWN -> i += it.getText().getScale()
                DisplayHandler.Order.UP -> i -= it.getText().getScale()
            }
        }

        height = i * 10
    }

    private fun drawLine(line: DisplayLine, x: Float, y: Float, maxWidth: Float) {
        when (align) {
            DisplayHandler.Align.LEFT -> line.drawLeft(
                x,
                y,
                maxWidth,
                background,
                backgroundColor,
                textColor
            )
            DisplayHandler.Align.RIGHT -> line.drawRight(
                x,
                y,
                maxWidth,
                background,
                backgroundColor,
                textColor
            )
            DisplayHandler.Align.CENTER -> line.drawCenter(
                x,
                y,
                maxWidth,
                background,
                backgroundColor,
                textColor
            )
        }
    }

    internal abstract fun createDisplayLine(text: String): DisplayLine

    override fun toString() =
        "Display{" +
                "shouldRender=$shouldRender, " +
                "renderX=$renderX, renderY=$renderY, " +
                "background=$background, backgroundColor=$backgroundColor, " +
                "textColor=$textColor, align=$align, order=$order, " +
                "minWidth=$minWidth, width=$width, height=$height, " +
                "lines=$lines" +
                "}"

}
