package asiantech.internship.summer.viewpagerTablayout

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import asiantech.internship.summer.R

class TabLayoutAdapter(fm: FragmentManager, private val mContext: Context) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> InfoFragment()
            else -> AnotherFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> mContext.resources.getString(R.string.pagetitle_text_home)
            1 -> mContext.resources.getString(R.string.pagetitle_text_info)
            else -> mContext.resources.getString(R.string.pagetitle_text_another)
        }
    }

    override fun getCount() = 3
}
