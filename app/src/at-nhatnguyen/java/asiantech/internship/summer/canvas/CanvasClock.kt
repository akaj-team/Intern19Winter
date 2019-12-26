package asiantech.internship.summer.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import asiantech.internship.summer.R
import java.util.*
import kotlin.math.cos
import kotlin.math.sin

class CanvasClock(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private var height = 0f
    private var width = 0f
    private var radius = 0f
    private lateinit var paint: Paint
    private var isInit: Boolean = false
    private var rect = Rect()
    private var padding = 0f
    private var fontSize = 0f
    private var handTruncation = 0f
    private var hourHandTruncation = 0f
    private var numeralSpacing = 0f


    private fun initClock() {
        height = getHeight().toFloat()
        width = getWidth().toFloat()
        padding = numeralSpacing + 50
        fontSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 25f,
                resources.displayMetrics).toInt().toFloat()
        val min = height.coerceAtMost(width).toInt()
        radius = min / 2 - padding
        handTruncation = (min / 20).toFloat()
        hourHandTruncation = (min / 9).toFloat()
        paint = Paint()
        isInit = true
    }

    override fun onDraw(canvas: Canvas) {
        if (!isInit) {
            initClock()
        }

        canvas.drawColor(Color.WHITE)
        dawText(canvas)
        drawNumeral(canvas)
        drawHands(canvas)
        drawPointCenter(canvas)
        postInvalidateDelayed(500)
        invalidate()
    }

    private fun drawHand(canvas: Canvas, loc: Double, isHour: Boolean, isSecond: Boolean) {
        val angle = Math.PI * loc / 30 - Math.PI / 2
        paint.strokeWidth = 10f
        paint.isAntiAlias = true
        val handRadius = when {
            isHour -> radius - handTruncation - hourHandTruncation
            isSecond -> radius
            else -> radius - handTruncation
        }

        when {
            isSecond -> paint.color = Color.YELLOW
            isHour -> paint.strokeWidth = 25f
            else -> {
                paint.strokeWidth = 15f
                paint.color = Color.BLACK
            }
        }
        canvas.drawLine(width / 2, height / 2,
                (width / 2 + cos(angle) * handRadius).toFloat(),
                (height / 2 + sin(angle) * handRadius).toFloat(),
                paint)
    }

    private fun drawHands(canvas: Canvas) {
        val c = Calendar.getInstance()
        var hour = c.get(Calendar.HOUR_OF_DAY)
        hour = if (hour > 12) {
            hour - 12
        } else {
            hour
        }
        drawHand(canvas, ((hour + c.get(Calendar.MINUTE) / 60f) * 5f).toDouble(), isHour = true, isSecond = false)
        drawHand(canvas, c.get(Calendar.MINUTE).toDouble(), isHour = false, isSecond = false)
        drawHand(canvas, c.get(Calendar.SECOND).toDouble(), isHour = false, isSecond = true)
    }

    private fun drawNumeral(canvas: Canvas) {
        paint.textSize = fontSize
        paint.color = Color.BLACK
        paint.strokeCap = Paint.Cap.ROUND
        for (number in 1..12) {
            val text = number.toString()
            paint.getTextBounds(text, 0, text.length, rect)
            val angle = Math.PI / 6 * (number - 3)
            val x = (width / 2 + cos(angle) * (radius * 0.7) - rect.width() / 2)
            val y = (height / 2 + sin(angle) * (radius * 0.7) + rect.height() / 2)

            canvas.drawText(text, x.toFloat(), y.toFloat(), paint)
            canvas.drawLine(width / 2 + (cos(angle) * radius * 0.9).toFloat(),
                    height / 2 + (sin(angle) * radius * 0.9).toFloat(),
                    width / 2 + (cos(angle) * radius * 0.8).toFloat(),
                    height / 2 + (sin(angle) * radius * 0.8).toFloat(), paint)
        }

        for (a in 1..60) {
            canvas.drawLine(width / 2 + (cos(Math.PI / 30 * (a - 15)) * radius).toFloat(),
                    height / 2 + (sin(Math.PI / 30 * (a - 15)) * radius).toFloat(),
                    width / 2 + (cos(Math.PI / 30 * (a - 15)) * radius * 0.9).toFloat(),
                    height / 2 + (sin(Math.PI / 30 * (a - 15)) * radius * 0.9).toFloat(), paint)
        }
    }

    private fun drawPointCenter(canvas: Canvas) {
        val paintPoint = Paint()
        paintPoint.style = Paint.Style.FILL
        paintPoint.color = Color.YELLOW
        canvas.drawCircle(width / 2, height / 2, 20F, paintPoint)
    }

    private fun dawText(canvas: Canvas) {
        canvas.drawText(resources.getString(R.string.text_love_lele), width * 0.4.toFloat(), (height * 0.58).toFloat(), paint.apply {
            color = Color.BLACK
            textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 18f,
                    resources.displayMetrics)
        })

        canvas.drawText(resources.getString(R.string.text_rourou), width * 0.45.toFloat(), (height * 0.6).toFloat(), paint.apply {
            color = Color.BLACK
            textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13f,
                    resources.displayMetrics)
        })
    }
}
