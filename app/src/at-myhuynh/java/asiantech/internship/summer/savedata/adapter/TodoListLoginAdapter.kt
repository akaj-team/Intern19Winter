package asiantech.internship.summer.savedata.adapter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import asiantech.internship.summer.savedata.entity.Tab

class TodoListLoginAdapter(fm: FragmentManager, private val tabLogins: MutableList<Tab>) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int) = tabLogins[position].fragment

    override fun getPageTitle(position: Int) = tabLogins[position].title

    override fun getCount() = tabLogins.size
}
