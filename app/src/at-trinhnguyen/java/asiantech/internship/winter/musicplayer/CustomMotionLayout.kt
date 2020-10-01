package asiantech.internship.winter.musicplayer

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import asiantech.internship.summer.R

class CustomMotionLayout @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : MotionLayout(context, attrs, defStyleAttr) {

    private var motionLayout: MotionLayout = LayoutInflater.from(context).inflate(R.layout.player_bar, this, false) as MotionLayout
    private val touchableArea: View
    private val clickableArea: View

    init {
        addView(motionLayout)
        touchableArea = motionLayout.findViewById(R.id.container)
        clickableArea = motionLayout.findViewById(R.id.container)
    }


    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val isInProgress = (motionLayout.progress > 0.0f && motionLayout.progress < 1.0f)
        val isInTarget = touchEventInsideTargetView(touchableArea, ev)

        return if (isInProgress || isInTarget) {
            super.onInterceptTouchEvent(ev)
        } else {
            true
        }
    }

    private fun touchEventInsideTargetView(v: View, ev: MotionEvent): Boolean {
        if (ev.x > v.left && ev.x < v.right) {
            if (ev.y > v.top && ev.y < v.bottom) {
                return true
            }
        }
        return false
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return false
    }

}
