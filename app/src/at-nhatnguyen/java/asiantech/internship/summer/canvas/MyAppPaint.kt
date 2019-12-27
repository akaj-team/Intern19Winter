package asiantech.internship.summer.canvas

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs

class MyAppPaint(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private lateinit var paint: Paint
    private var width = 0f
    private var height = 0f
    private lateinit var drawBitmap: Bitmap
    private lateinit var path: Path
    private var x: Float? = 0f
    private var y: Float? = 0f
    private lateinit var extraCanvas: Canvas
    private var erase: Boolean = false

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(drawBitmap, 0f, 0f, null)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        width = getWidth().toFloat()
        height = getHeight().toFloat()
        drawBitmap = Bitmap.createBitmap(width.toInt(), height.toInt(), Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(drawBitmap)
        extraCanvas.drawColor(Color.WHITE)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event?.x
        val y = event?.y
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> x?.let { y?.let { it1 -> touchStart(it, it1) } }
            MotionEvent.ACTION_MOVE -> {
                x?.let { y?.let { it1 -> touchMove(it, it1) } }
                invalidate()
            }
            MotionEvent.ACTION_UP -> touchUp()
        }
        return true
    }

    private fun touchStart(x: Float, y: Float) {
        path = Path()
        path.moveTo(x, y)
        this.x = x
        this.y = y
    }

    private fun touchMove(x: Float, y: Float) {
        paint = Paint()
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
        paint.color = Color.RED
        paint.strokeWidth = 20f
        paint.isAntiAlias = true
        paint.strokeJoin = Paint.Join.ROUND
        val dX: Float = abs(x - this.x!!)
        val dY: Float = abs(y - this.y!!)
        if (erase == false) {
            if (dX > 4 || dY > 4) {
                path.quadTo(this.x!!, this.y!!, (x + this.x!!) / 2, (y + this.y!!) / 2)
                this.x = x
                this.y = y
                extraCanvas.drawPath(path, paint)
            }
        } else {
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
            if (dX > 4 || dY > 4) {
                path.quadTo(this.x!!, this.y!!, (x + this.x!!) / 2, (y + this.y!!) / 2)
                this.x = x
                this.y = y
                extraCanvas.drawPath(path, paint.apply {
                    strokeWidth = 80f
                })
            }
        }
    }

    private fun touchUp() {
        path.reset()

    }

    fun setErase(isErase: Boolean) {
        paint = Paint()
        this.erase = isErase
        Log.d("xxx", "$erase")
    }

}
