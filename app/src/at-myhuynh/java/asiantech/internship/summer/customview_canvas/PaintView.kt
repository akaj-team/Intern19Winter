package asiantech.internship.summer.customview_canvas

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class PaintView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var paint = Paint()
    private var path = Path()
    private var curX = 0f
    private var curY = 0f
    private var startX = 0f
    private var startY = 0f
    private var isEraser = false
    private lateinit var drawBitmap: Bitmap
    private lateinit var drawCanvas: Canvas
    private var color = Color.BLACK

    companion object {
        private const val PAINT_WIDTH = 8f
        private const val ERASER_WIDTH = 20f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(drawBitmap, 0f, 0f, null)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        drawBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        drawCanvas = Canvas(drawBitmap)
    }

    fun setEraser(isEraser: Boolean) {
        this.isEraser = isEraser
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = x
                startY = y
                actionDown(x, y)
            }
            MotionEvent.ACTION_MOVE -> {
                actionMove(x, y)
                invalidate()
            }
            MotionEvent.ACTION_UP -> performClick()
        }

        return true
    }

    override fun performClick(): Boolean {
        path.reset()
        return super.performClick()
    }

    private fun actionDown(x: Float, y: Float) {
        path.moveTo(x, y)
        curX = x
        curY = y
    }

    private fun actionMove(x: Float, y: Float) {
        paint = Paint().apply {
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            strokeWidth = PAINT_WIDTH
            isAntiAlias = true
        }
        path.quadTo(curX, curY, (x + curX) / 2, (y + curY) / 2)
        curX = x
        curY = y

        if (!isEraser) {
            paint.xfermode = null
            paint.color = color
            drawCanvas.drawPath(path, paint)
        } else {
            paint.strokeWidth = ERASER_WIDTH
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
            drawCanvas.drawPath(path, paint)
        }
    }

    fun setPaintColor(color: Int) {
        this.color = color
    }
}
