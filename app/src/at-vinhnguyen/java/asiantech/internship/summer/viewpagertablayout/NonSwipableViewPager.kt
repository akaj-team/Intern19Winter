package asiantech.internship.summer.viewpagertablayout

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class NonSwipableViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {
    //For deny swipeable between pages
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

    override fun performClick(): Boolean {
        super.performClick()
        return false
    }
}
