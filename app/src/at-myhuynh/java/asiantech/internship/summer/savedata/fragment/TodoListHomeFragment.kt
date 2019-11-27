package asiantech.internship.summer.savedata.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import asiantech.internship.summer.R
import asiantech.internship.summer.savedata.Constants.Companion.KEY_USER_ID
import asiantech.internship.summer.savedata.Constants.Companion.NAV_ADD_TODO
import asiantech.internship.summer.savedata.Constants.Companion.NAV_EDIT_PROFILE
import asiantech.internship.summer.savedata.Constants.Companion.NAV_LOG_OUT
import asiantech.internship.summer.savedata.Constants.Companion.TAB_DONE
import asiantech.internship.summer.savedata.Constants.Companion.TAB_TODO
import asiantech.internship.summer.savedata.TodoListActivity
import asiantech.internship.summer.savedata.adapter.NavAdapter
import asiantech.internship.summer.savedata.adapter.TodoListHomeAdapter
import asiantech.internship.summer.savedata.data.TodoListDatabaseHelper
import asiantech.internship.summer.savedata.entity.NavItem
import asiantech.internship.summer.savedata.entity.Tab
import asiantech.internship.summer.savedata.entity.User
import asiantech.internship.summer.savedata.interfaces.NavItemOnClick
import kotlinx.android.synthetic.`at-myhuynh`.fragment_todo_list_home.*

class TodoListHomeFragment : Fragment() {
    private lateinit var navItems: MutableList<NavItem>
    private lateinit var todoListDatabase: TodoListDatabaseHelper
    private lateinit var preferences: SharedPreferences
    private lateinit var tabTodos: MutableList<Tab>
    private var userId: Int = -1
    private lateinit var user: User

    companion object {
        private const val FILE_NAME = "userLogin"
        fun newInstance(userId: Int) = TodoListHomeFragment().apply {
            arguments = Bundle().apply {
                putInt(KEY_USER_ID, userId)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userId = it.getInt(KEY_USER_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_todo_list_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        todoListDatabase = TodoListDatabaseHelper(requireContext())
        user = todoListDatabase.readUser(userId)
        initNav()
        initTabs()

        val vpAdapter = TodoListHomeAdapter(childFragmentManager, tabTodos)
        vpHome.adapter = vpAdapter
        tlHome.setupWithViewPager(vpHome)

        val navAdapter = NavAdapter(user, navItems)
        rvNavigation.layoutManager = LinearLayoutManager(requireContext())
        rvNavigation.adapter = navAdapter
        setOnClickNavItem(navAdapter)

        vpHome.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                vpAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun setOnClickNavItem(adapter: NavAdapter) {
        adapter.setOnclickNavItem(object : NavItemOnClick {
            override fun onClick(navItem: NavItem) {
                if (navItem.title == NAV_LOG_OUT) {
                    preferences = requireContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
                    val preferencesEditor = preferences.edit()
                    preferencesEditor.remove(KEY_USER_ID)
                    preferencesEditor.apply()
                    (activity as? TodoListActivity)?.removeFragmentInBackStack(navItem.toFragment)
                }
                (activity as? TodoListActivity)?.replaceFragment(navItem.toFragment, navItem.isAddToBackStack)
            }
        })
    }

    private fun initTabs() {
        tabTodos = mutableListOf()
        tabTodos.apply {
            add(Tab(TodoListHomeTodoFragment.newInstance(userId), TAB_TODO))
            add(Tab(TodoListHomeDoneFragment.newInstance(userId), TAB_DONE))
        }
    }

    private fun initNav() {
        navItems = mutableListOf()
        navItems.apply {
            add(NavItem((R.drawable.ic_people_black_24dp), NAV_EDIT_PROFILE, TodoListEditProfileFragment.newInstance(userId), true))
            add(NavItem((R.drawable.ic_add_circle_black_24dp), NAV_ADD_TODO, TodoListAddTodoFragment.newInstance(userId = userId), true))
            add(NavItem((R.drawable.ic_assignment_return_black_24dp), NAV_LOG_OUT, TodoListLoginFragment.newInstance(), false))
        }
    }
}
