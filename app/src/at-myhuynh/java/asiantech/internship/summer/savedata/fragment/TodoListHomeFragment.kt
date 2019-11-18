package asiantech.internship.summer.savedata.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import asiantech.internship.summer.R
import asiantech.internship.summer.savedata.TodoListActivity
import asiantech.internship.summer.savedata.adapter.NavAdapter
import asiantech.internship.summer.savedata.adapter.TodoListHomeAdapter
import asiantech.internship.summer.savedata.entity.NavItem
import asiantech.internship.summer.savedata.entity.Todo
import asiantech.internship.summer.savedata.entity.User
import asiantech.internship.summer.savedata.interfaces.NavItemOnClick
import kotlinx.android.synthetic.`at-myhuynh`.fragment_todo_list_home.*

class TodoListHomeFragment : Fragment() {
    private lateinit var mTodoLists: MutableList<Todo>
    private lateinit var mNavItems: MutableList<NavItem>
    private var mUser: User = User(1, "ToDo List", "", "", R.drawable.ic_man)

    companion object {
        fun newInstance() = TodoListHomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_todo_list_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initData()
        initNav()

        vpHome.adapter = TodoListHomeAdapter(childFragmentManager, mTodoLists)
        tlHome.setupWithViewPager(vpHome)

        val navAdapter = NavAdapter(mUser, mNavItems)
        rvNavigation.layoutManager = LinearLayoutManager(requireContext())
        rvNavigation.adapter = navAdapter
        setOnClickNavItem(navAdapter)
    }

    private fun setOnClickNavItem(adapter: NavAdapter) {
        adapter.setOnclickNavItem(object : NavItemOnClick {
            override fun onClick(navItem: NavItem) {
                (activity as? TodoListActivity)?.replaceFragment(navItem.toFragment, navItem.isAddToBackStack)
            }

        })
    }

    private fun initData() {
        mTodoLists = mutableListOf()
        for (i in 0..10) {
            mTodoLists.add(Todo(i, "Todo $i", i % 2 == 0))
        }
    }

    private fun initNav() {
        mNavItems = mutableListOf()
        mNavItems.apply {
            add(NavItem((R.drawable.ic_people_black_24dp), "Edit Profile", TodoListEditProfileFragment.newInstance(), true))
            add(NavItem((R.drawable.ic_add_circle_black_24dp), "Add Todo", TodoListAddNoteFragment.newInstance(), true))
            add(NavItem((R.drawable.ic_assignment_return_black_24dp), "Log Out", TodoListLoginFragment.newInstance(), false))
        }
    }
}
