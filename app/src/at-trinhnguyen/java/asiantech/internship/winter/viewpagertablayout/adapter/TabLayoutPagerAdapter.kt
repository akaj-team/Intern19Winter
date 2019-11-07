package asiantech.internship.winter.viewpagertablayout.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import asiantech.internship.summer.R
import asiantech.internship.winter.viewpagertablayout.ui.OneFragment
import asiantech.internship.winter.viewpagertablayout.ui.ThreeFragment
import asiantech.internship.winter.viewpagertablayout.ui.TwoFragment

class TabLayoutPagerAdapter(fragmentManager: FragmentManager, private val mContext: Context) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> OneFragment()
            1 -> TwoFragment()
            else -> ThreeFragment()
        }
    }

    override fun getCount() = 3

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> mContext.getString(R.string.tablayout_title_home)
            1 -> mContext.getString(R.string.tablayout_title_info)
            else -> mContext.getString(R.string.tablayout_title_another)
        }
    }
}
