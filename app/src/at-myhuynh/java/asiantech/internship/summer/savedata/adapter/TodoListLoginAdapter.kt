package asiantech.internship.summer.savedata.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import asiantech.internship.summer.savedata.fragment.TodoListTabLoginFragment
import asiantech.internship.summer.savedata.fragment.TodoListTabRegisterFragment

class TodoListLoginAdapter(fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> TodoListTabLoginFragment()
            else -> TodoListTabRegisterFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Login"
            else -> "Register"
        }
    }

    override fun getCount() = 2
}
