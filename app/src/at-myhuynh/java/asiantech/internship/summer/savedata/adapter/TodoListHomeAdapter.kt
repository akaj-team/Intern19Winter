package asiantech.internship.summer.savedata.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import asiantech.internship.summer.savedata.entity.Todo
import asiantech.internship.summer.savedata.fragment.TodoListHomeDoneFragment
import asiantech.internship.summer.savedata.fragment.TodoListHomeTodoFragment

class TodoListHomeAdapter(fm: FragmentManager, private val mTodoLists: MutableList<Todo>) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> TodoListHomeTodoFragment.newInstance(mTodoLists)
            else -> TodoListHomeDoneFragment.newInstance(mTodoLists)
        }
    }

    override fun getCount() = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Todo"
            else -> "Done"
        }
    }
}
