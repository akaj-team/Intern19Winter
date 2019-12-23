package asiantech.internship.summer.retrofit.adapter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import asiantech.internship.summer.retrofit.model.Tab

class TodoListHomeAdapter(fm: FragmentManager, private val tabTodos: MutableList<Tab>) :
        FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int) = tabTodos[position].fragment

    override fun getCount() = tabTodos.size

    override fun getPageTitle(position: Int) = tabTodos[position].title

    override fun getItemPosition(`object`: Any) = PagerAdapter.POSITION_NONE
}
