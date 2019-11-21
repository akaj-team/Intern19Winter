package asiantech.internship.winter.viewpagertablayout.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import asiantech.internship.winter.viewpagertablayout.Page

class TabLayoutPagerAdapter(fragmentManager: FragmentManager, private val pages: List<Page>) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return pages[position].fragment
    }

    override fun getCount() = pages.size

    override fun getPageTitle(position: Int): CharSequence? {
        return pages[position].title
    }
}
