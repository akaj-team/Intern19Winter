package asiantech.internship.winter.viewpagertablayout.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import asiantech.internship.winter.viewpagertablayout.IndicatorEnum

class IndicatorPagerAdapter(private val mContext: Context) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val indicatorEnum = IndicatorEnum.values()[position]
        val layout = LayoutInflater.from(mContext).inflate(indicatorEnum.getLayoutResId(), container, false)
        container.addView(layout)
        return layout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return IndicatorEnum.values().size
    }
}
