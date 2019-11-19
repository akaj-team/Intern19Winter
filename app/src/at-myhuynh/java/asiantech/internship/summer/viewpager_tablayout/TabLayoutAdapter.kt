package asiantech.internship.summer.viewpager_tablayout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabLayoutAdapter(fm: FragmentManager, private val listTabs: MutableList<Tab>) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return listTabs[position].fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return listTabs[position].title
    }

    override fun getCount() = listTabs.size
}
