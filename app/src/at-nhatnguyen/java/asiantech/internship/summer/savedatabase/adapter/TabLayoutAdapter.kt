package asiantech.internship.summer.savedatabase.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.ListFragment
import asiantech.internship.summer.savedatabase.fragment.DoneFragment
import asiantech.internship.summer.savedatabase.fragment.ItemToDoFragment

class TabLayoutAdapter(fm: FragmentManager, private val listFragments:MutableList<asiantech.internship.summer.savedatabase.items.ListFragment>) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return listFragments[position].listFragment
    }

    override fun getCount(): Int {
        return listFragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return listFragments[position].listTitle
    }
}