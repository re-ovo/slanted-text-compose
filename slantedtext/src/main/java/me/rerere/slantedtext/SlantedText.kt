package me.rerere.slantedtext

import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.sin

// the rotation angle of the slanted text
private val aTan = atan(1f)

/**
 * A composable that draws a slanted text.
 *
 * @param text the text to draw
 * @param textSize the text size
 * @param bold whether the text should be bold
 * @param thickness the thickness of the background
 * @param padding the padding of the background to border
 * @param backGroundColor the background color
 * @param textColor the text color
 * @param slantedMode the slanted mode(position of text)
 * @param content your content
 *
 * @author [RE](https://github.com/re-ovo)
 */
@Composable
fun SlantedText(
    text: String,
    textSize: TextUnit,
    bold: Boolean = true,
    thickness: Dp,
    padding: Dp,
    backGroundColor: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    slantedMode: SlantedMode,
    content: @Composable () -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier.wrapContentSize()
    ) {
        content()
        Canvas(
            modifier = Modifier
                .matchParentSize()
                .clipToBounds(),
            onDraw = {
                val width = size.width
                val height = size.height

                val thicknessPx = thickness.toPx()
                    // Make sure the thickness is greater than the text height (15 px)
                    .coerceAtLeast(textSize.toPx() + 15)
                val paddingPx = padding.toPx()

                val paddingWidth = (paddingPx / sin(aTan))
                val paddingHeight = (paddingPx / cos(aTan))

                val thicknessWidth = (thicknessPx / sin(aTan))
                val thicknessHeight = (thicknessPx / cos(aTan))

                val path = getBackGroundPath(
                    slantedMode,
                    thicknessWidth,
                    thicknessHeight,
                    paddingWidth,
                    paddingHeight,
                    width,
                    height
                )
                drawPath(path, backGroundColor)

                drawContext.canvas.nativeCanvas.apply {
                    save()

                    val (x, y) = calculateTextPosition(
                        slantedMode,
                        textSize.toPx(),
                        paddingPx,
                        thicknessPx,
                        width,
                        height
                    )

                    rotate(getRotateDegree(slantedMode), x, y)
                    drawText(text, x, y, Paint().apply {
                        this.textSize = textSize.toPx()
                        this.color = textColor.toArgb()
                        this.textAlign = Paint.Align.CENTER
                        this.isAntiAlias = true
                        this.typeface = if(!bold) Typeface.DEFAULT else Typeface.DEFAULT_BOLD
                    })

                    restore()
                }
            }
        )
    }
}

private fun getBackGroundPath(
    slantedMode: SlantedMode,
    thicknessWidth: Float,
    thicknessHeight: Float,
    paddingWidth: Float,
    paddingHeight: Float,
    width: Float,
    height: Float
): Path {
    return Path().apply {
        when (slantedMode) {
            SlantedMode.TOP_LEFT -> {
                moveTo(thicknessWidth + paddingWidth, 0f)
                lineTo(paddingWidth, 0f)
                lineTo(0f, paddingHeight)
                lineTo(0f, thicknessHeight + paddingHeight)
            }
            SlantedMode.TOP_RIGHT -> {
                moveTo(width - thicknessWidth - paddingWidth, 0f)
                lineTo(width - paddingWidth, 0f)
                lineTo(width, paddingHeight)
                lineTo(width, thicknessHeight + paddingHeight)
            }
            SlantedMode.BOTTOM_LEFT -> {
                moveTo(thicknessWidth + paddingWidth, height)
                lineTo(paddingWidth, height)
                lineTo(0f, height - paddingHeight)
                lineTo(0f, height - thicknessHeight - paddingHeight)
            }
            SlantedMode.BOTTOM_RIGHT -> {
                moveTo(width - thicknessWidth - paddingWidth, height)
                lineTo(width - paddingWidth, height)
                lineTo(width, height - paddingHeight)
                lineTo(width, height - thicknessHeight - paddingHeight)
            }
        }
    }
}

private fun getRotateDegree(slantedMode: SlantedMode): Float = when (slantedMode) {
    SlantedMode.TOP_LEFT -> Math.toDegrees(-aTan.toDouble())
    SlantedMode.TOP_RIGHT -> Math.toDegrees(aTan.toDouble())
    SlantedMode.BOTTOM_LEFT -> Math.toDegrees(aTan.toDouble())
    SlantedMode.BOTTOM_RIGHT -> Math.toDegrees(-aTan.toDouble())
}.toFloat()

private fun calculateTextPosition(
    slantedMode: SlantedMode,
    textHeight: Float,
    padding: Float,
    thickness: Float,
    width: Float,
    height: Float
): Pair<Float, Float> = when (slantedMode) {
    SlantedMode.TOP_LEFT -> {
        val x = (padding + (thickness / 2f) + textHeight / 2f) * sin(aTan)
        val y = (padding + (thickness / 2f) + textHeight / 2f) * cos(aTan)
        x to y
    }
    SlantedMode.TOP_RIGHT -> {
        val x = width - (padding + (thickness / 2f) + (textHeight / 2f)) * sin(aTan)
        val y = (padding + (thickness / 2f) + textHeight / 2f) * cos(aTan)
        x to y
    }
    SlantedMode.BOTTOM_LEFT -> {
        val x = (padding + (thickness / 2f) - (textHeight / 2f)) * sin(aTan)
        val y = height - (padding + (thickness / 2f) - (textHeight / 2f)) * cos(aTan)
        x to y
    }
    SlantedMode.BOTTOM_RIGHT -> {
        val x = width - (padding + (thickness / 2f) - (textHeight / 2f)) * sin(aTan)
        val y = height - (padding + (thickness / 2f) - (textHeight / 2f)) * cos(aTan)
        x to y
    }
}

/**
 * Represents the related position of a slanted text.
 */
enum class SlantedMode {
    TOP_LEFT,
    TOP_RIGHT,
    BOTTOM_LEFT,
    BOTTOM_RIGHT
}