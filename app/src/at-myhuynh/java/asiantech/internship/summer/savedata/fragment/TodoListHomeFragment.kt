package asiantech.internship.summer.savedata.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import asiantech.internship.summer.R
import asiantech.internship.summer.savedata.TodoListActivity
import asiantech.internship.summer.savedata.adapter.NavAdapter
import asiantech.internship.summer.savedata.adapter.TodoListHomeAdapter
import asiantech.internship.summer.savedata.data.TodoListDatabaseHelper
import asiantech.internship.summer.savedata.entity.NavItem
import asiantech.internship.summer.savedata.entity.Todo
import asiantech.internship.summer.savedata.entity.User
import asiantech.internship.summer.savedata.interfaces.NavItemOnClick
import kotlinx.android.synthetic.`at-myhuynh`.fragment_todo_list_home.*

class TodoListHomeFragment : Fragment() {
    private lateinit var mTodoLists: MutableList<Todo>
    private lateinit var mNavItems: MutableList<NavItem>
    private lateinit var todoListDatabase: TodoListDatabaseHelper
    private var mUser: User = User(1, "ToDo List", "", "", R.drawable.ic_man)

    companion object {
        private const val KEY_USER_ID = "userId"
        fun newInstance(idUser: Int = -1) = TodoListHomeFragment().apply {
            arguments = Bundle().apply {
                putInt(KEY_USER_ID, idUser)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_todo_list_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        todoListDatabase = TodoListDatabaseHelper(requireContext())
        readTodos()
        initNav()

        val vpAdapter = TodoListHomeAdapter(childFragmentManager, mTodoLists)
        vpHome.adapter = vpAdapter
        tlHome.setupWithViewPager(vpHome)

        val navAdapter = NavAdapter(mUser, mNavItems)
        rvNavigation.layoutManager = LinearLayoutManager(requireContext())
        rvNavigation.adapter = navAdapter
        setOnClickNavItem(navAdapter)

        /**/
        vpHome.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                readTodos()
                vpAdapter.notifyDataSetChanged()
            }

            override fun onPageSelected(position: Int) {}
        })
    }

    private fun readTodos() {
        mTodoLists = todoListDatabase.readTodos()
    }

    private fun setOnClickNavItem(adapter: NavAdapter) {
        adapter.setOnclickNavItem(object : NavItemOnClick {
            override fun onClick(navItem: NavItem) {
                if (navItem.mTitle == "Log Out") {
                    (activity as? TodoListActivity)?.removeFragmentBackStack()
                }
                (activity as? TodoListActivity)?.replaceFragment(navItem.toFragment, navItem.isAddToBackStack)
            }

        })
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
