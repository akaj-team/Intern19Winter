package asiantech.internship.summer.viewpager_tablayout

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(fm: FragmentManager, private val listTabs: MutableList<Tab>) :
        FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int) = listTabs[position].fragment

    override fun getCount() = listTabs.size
}
