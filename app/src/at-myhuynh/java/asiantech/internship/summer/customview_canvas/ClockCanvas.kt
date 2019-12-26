package asiantech.internship.summer.customview_canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import java.util.*
import kotlin.math.cos
import kotlin.math.sin

class ClockCanvas(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var paint: Paint = Paint()
    private var widthClock = 0
    private var heightClock = 0
    private var padding = 0
    private var numeralSpacing = 0
    private var radius = 0
    private val rect = Rect()
    private var isInit = false
    private val clockHours = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (!isInit) {
            heightClock = height
            widthClock = width
            padding = numeralSpacing + 50
            val minAttr = widthClock.coerceAtMost(heightClock)
            radius = minAttr / 2
            isInit = true
        }

        paint.reset()
        paint.color = Color.BLACK
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 5f
        paint.isAntiAlias = true

        drawMinutes(paint, canvas)

        drawText(paint, canvas)

        drawTime(paint, canvas)

        drawCirclePoint(paint, canvas)

        postInvalidateDelayed(500)
        invalidate()
    }

    private fun drawCirclePoint(paint: Paint, canvas: Canvas?) {
        paint.style = Paint.Style.FILL
        paint.color = Color.argb(255, 213, 153, 0)
        canvas?.drawCircle(widthClock / 2f, heightClock / 2f, 12f, paint)
    }

    private fun drawTime(paint: Paint, canvas: Canvas?) {
        val calendar = Calendar.getInstance()
        var hour = calendar.get(Calendar.HOUR_OF_DAY)
        hour = if (hour > 12) hour - 12 else hour
        canvas?.let {
            drawHandLine(it, (hour + calendar.get(Calendar.MINUTE) / 60f) * 5f, isHour = true, isSecond = false, paintLine = paint)
            drawHandLine(it, (calendar.get(Calendar.MINUTE)).toFloat(), isHour = false, isSecond = false, paintLine = paint)
            drawHandLine(it, (calendar.get(Calendar.SECOND)).toFloat(), isHour = false, isSecond = true, paintLine = paint)
        }
    }

    private fun drawText(paint: Paint, canvas: Canvas?) {
        var text = "LOVE LELE"
        paint.getTextBounds(text, 0, text.length, rect)
        var xText = widthClock / 2 - rect.width() / 2
        var yText = heightClock / 2 + (radius * 0.45) - rect.height() / 2
        canvas?.drawText(text, xText.toFloat(), yText.toFloat(), paint)

        text = "Rourou"
        paint.getTextBounds(text, 0, text.length, rect)
        xText = widthClock / 2 - rect.width() / 2
        yText = heightClock / 2 + (radius * 0.55) - rect.height() / 2
        canvas?.drawText(text, xText.toFloat(), yText.toFloat(), paint)
    }

    private fun drawMinutes(paint: Paint, canvas: Canvas?) {
        paint.strokeCap = Paint.Cap.ROUND
        clockHours.forEach {
            paint.getTextBounds("$it", 0, "$it".length, rect)
            val angle = Math.PI / 6 * (it - 3)
            val x = widthClock / 2 + cos(angle) * (radius * 0.8) - rect.width() / 2
            val y = heightClock / 2 + sin(angle) * (radius * 0.8) + rect.height() / 2
            canvas?.drawText("$it", x.toFloat(), y.toFloat(), paint)

            val startX = (widthClock / 2f + cos(angle) * (radius * 0.85f)).toFloat()
            val startY = (heightClock / 2f + sin(angle) * (radius * 0.85f)).toFloat()
            val endX = (widthClock / 2f + cos(angle) * (radius * 0.9f)).toFloat()
            val endY = (heightClock / 2f + sin(angle) * (radius * 0.9f)).toFloat()

            canvas?.drawLine(startX, startY, endX, endY, paint)
        }

        for (i in 1..60) {
            val angle = Math.PI / 30 * (i - 15)
            val startX = (widthClock / 2f + cos(angle) * (radius * 0.88f)).toFloat()
            val startY = (heightClock / 2f + sin(angle) * (radius * 0.88f)).toFloat()
            val endX = (widthClock / 2f + cos(angle) * (radius * 0.9f)).toFloat()
            val endY = (heightClock / 2f + sin(angle) * (radius * 0.9f)).toFloat()
            canvas?.drawLine(startX, startY, endX, endY, paint)
        }
    }

    private fun drawHandLine(canvas: Canvas, moment: Float, isHour: Boolean, isSecond: Boolean, paintLine: Paint) {
        val angle = Math.PI * moment / 30f - Math.PI / 2f
        paintLine.setShadowLayer(2f, 3f, 6f, Color.DKGRAY)

        val handRadius = if (isHour) {
            radius * 0.6
        } else {
            radius * 0.8
        }
        when {
            isSecond -> {
                paintLine.color = Color.argb(255, 213, 153, 0)
                paintLine.strokeWidth = 5f
                canvas.drawLine(widthClock / 2f, heightClock / 2f, (widthClock / 2f + cos(angle) * handRadius).toFloat(), (heightClock / 2f + sin(angle) * handRadius).toFloat(), paintLine)
            }
            isHour -> {
                paintLine.strokeWidth = 8f
                canvas.drawLine(widthClock / 2f, heightClock / 2f, (widthClock / 2f + cos(angle) * handRadius).toFloat(), (heightClock / 2f + sin(angle) * handRadius).toFloat(), paintLine)
            }
            else -> {
                paintLine.strokeWidth = 6f
                canvas.drawLine(widthClock / 2f, heightClock / 2f, (widthClock / 2f + cos(angle) * handRadius).toFloat(), (heightClock / 2f + sin(angle) * handRadius).toFloat(), paintLine)
            }
        }

    }
}
