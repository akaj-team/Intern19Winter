package asiantech.internship.winter.canvas

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
import kotlin.math.min
import kotlin.math.sin


class AnalogClock : View {
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var clockHeight = 0f
    private var clockWidth = 0f
    private var padding = 0f
    private var radius = 0f
    private lateinit var paint: Paint
    private var rect = Rect()
    private var isInit = false

    override fun onDraw(canvas: Canvas?) {
        if (!isInit) {
            paint = Paint()
            clockHeight = height.toFloat()
            clockWidth = width.toFloat()
            padding = 20f // spacing from the circle border
            radius = min(clockHeight, clockWidth) / 2 - padding
            isInit = true
        }

        paint.apply {
            reset()
            color = Color.BLACK
            style = Paint.Style.STROKE
            strokeWidth = 4F
            isAntiAlias = true
        }
        //draw circle
        //canvas?.drawCircle((clockWidth / 2), (clockHeight / 2), radius, paint)
        paint.style = Paint.Style.FILL


        /** border of hours  */
        paint.textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20f, resources.displayMetrics)// set font size (optional)
        for (hour in 1..12) {
            paint.getTextBounds(hour.toString(), 0, hour.toString().length, rect) // for circle-wise bounding
            val angle = (2 * Math.PI / 12 * (hour - 3)).toFloat()
            canvas?.drawText(
                    hour.toString(),
                    (clockWidth / 2 + cos(angle) * (radius * 0.8) - rect.width() / 2).toFloat(),
                    (clockHeight / 2 + sin(angle) * (radius * 0.8) + rect.height() / 2).toFloat(),
                    paint
            )

            canvas?.drawLine(
                    clockWidth / 2 + (cos(angle) * radius),
                    clockHeight / 2 + (sin(angle) * radius),
                    clockWidth / 2 + (cos(angle) * (radius * 0.9)).toFloat(),
                    clockHeight / 2 + (sin(angle) * (radius * 0.9)).toFloat(),
                    paint.apply {
                        strokeWidth = 15f
                        strokeCap = Paint.Cap.ROUND
                    })
        }

        for (minute in 1..60) {
            val angle = (2 * Math.PI / 60 * (minute - 15)).toFloat()
            canvas?.drawLine(
                    clockWidth / 2 + (cos(angle) * radius * 0.99).toFloat(),
                    clockHeight / 2 + (sin(angle) * radius * 0.99).toFloat(),
                    clockWidth / 2 + (cos(angle) * (radius * 0.94)).toFloat(),
                    clockHeight / 2 + (sin(angle) * (radius * 0.94)).toFloat(),
                    paint.apply {
                        strokeWidth = 11f
                        strokeCap = Paint.Cap.ROUND
                    })

        }
        paint.textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16f, resources.displayMetrics)
        paint.getTextBounds(context.getString(R.string.analogclock_text_love_lele), 0, context.getString(R.string.analogclock_text_love_lele).length, rect)
        canvas?.drawText(
                context.getString(R.string.analogclock_text_love_lele),
                (clockWidth / 2 - rect.width() / 2),
                (clockHeight / 2 + rect.height() / 2 + radius * 0.3).toFloat(),
                paint
        )

        paint.textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12f, resources.displayMetrics)
        paint.getTextBounds(context.getString(R.string.analogclock_text_rourou), 0, context.getString(R.string.analogclock_text_rourou).length, rect)
        canvas?.drawText(
                context.getString(R.string.analogclock_text_rourou),
                (clockWidth / 2 - rect.width() / 2),
                (clockHeight / 2 + rect.height() / 2 + radius * 0.4).toFloat(),
                paint
        )

        /** draw clock hands to represent the every single time */
        paint.strokeCap = Paint.Cap.BUTT

        val calendar: Calendar = Calendar.getInstance()
        var hour: Int = calendar.get(Calendar.HOUR_OF_DAY)
        hour = if (hour > 12) hour - 12 else hour

        canvas?.let { it ->
            drawHandLine(it, (hour + calendar.get(Calendar.MINUTE) / 60f) * 5f, isHour = true, isSecond = false)  // draw hours

            drawHandLine(it, calendar.get(Calendar.MINUTE).toFloat(), isHour = false, isSecond = false) // draw minutes

            drawHandLine(it, calendar.get(Calendar.SECOND).toFloat(), isHour = false, isSecond = true) // draw seconds
        }

        //dot in center
        paint.color = Color.argb(255, 213, 153, 0)
        canvas?.drawCircle((clockWidth / 2), (clockHeight / 2), 22f, paint)
        paint.color = Color.BLACK
        /** invalidate the appearance for next representation of time   */
        postInvalidateDelayed(500)
        invalidate()
    }

    private fun drawHandLine(canvas: Canvas, moment: Float, isHour: Boolean, isSecond: Boolean) {
        val angle = Math.PI * moment / 30 - Math.PI / 2
        val handRadius = when {
            isSecond -> {
                paint.color = Color.argb(255, 213, 153, 0)
                paint.strokeWidth = 10f
                radius * 0.97
            }
            isHour -> {
                paint.strokeWidth = 30f
                radius * 0.62
            }
            else -> {
                paint.strokeWidth = 20f
                radius * 0.82
            }
        }
        canvas.drawLine(clockWidth / 2, clockHeight / 2, (clockWidth / 2 + cos(angle) * handRadius).toFloat(), (clockHeight / 2 + sin(angle) * handRadius).toFloat(), paint)
    }
}
