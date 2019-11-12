package asiantech.internship.summer.viewpager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import asiantech.internship.summer.R

class IndicatorAdapter(private val mContext: Context) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val arrResult = mutableListOf(R.layout.fragment_step_one,R.layout.fragment_step_two,R.layout.fragment_step_three)
        val inflater = LayoutInflater.from(mContext)
        val layout = inflater.inflate(arrResult[position], container, false) as ViewGroup
        container.addView(layout)
        return layout
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }

    override fun getCount(): Int {
        return 3
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            1 ->""
            2->""
            else -> ""
        }
    }
}
