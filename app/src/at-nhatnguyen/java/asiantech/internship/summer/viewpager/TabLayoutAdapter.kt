package asiantech.internship.summer.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class TabLayoutAdapter(fm: FragmentManager, private val listModel: MutableList<Model>) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return listModel[position].listFragment
    }

    override fun getCount(): Int {
        return listModel.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return listModel[position].listTitle
    }
}
