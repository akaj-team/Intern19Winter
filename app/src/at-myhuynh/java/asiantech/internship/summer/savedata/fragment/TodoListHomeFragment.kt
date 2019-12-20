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
import asiantech.internship.summer.savedata.Constants.Companion.NAV_LOG_OUT
import asiantech.internship.summer.savedata.Constants.Companion.TAB_DONE
import asiantech.internship.summer.savedata.Constants.Companion.TAB_TODO
import asiantech.internship.summer.savedata.TodoListActivity
import asiantech.internship.summer.savedata.adapter.NavAdapter
import asiantech.internship.summer.savedata.adapter.TodoListHomeAdapter
import asiantech.internship.summer.savedata.entity.NavItem
import asiantech.internship.summer.savedata.entity.Tab
import asiantech.internship.summer.savedata.interfaces.NavItemOnClick
import kotlinx.android.synthetic.`at-myhuynh`.fragment_todo_list_home.*

class TodoListHomeFragment : Fragment() {
    private lateinit var navItems: MutableList<NavItem>
    private lateinit var preferences: SharedPreferences
    private lateinit var tabTodos: MutableList<Tab>

    companion object {
        private const val FILE_NAME = "userLogin"
        fun newInstance() = TodoListHomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_todo_list_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initNav()
        initTabs()

        val vpAdapter = TodoListHomeAdapter(childFragmentManager, tabTodos)
        vpHome.adapter = vpAdapter
        tlHome.setupWithViewPager(vpHome)

        navItems = mutableListOf()
        val navAdapter = NavAdapter(navItems)
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
            add(Tab(TodoListHomeTodoFragment.newInstance(), TAB_TODO))
            add(Tab(TodoListHomeDoneFragment.newInstance(), TAB_DONE))
        }
    }

    private fun initNav() {
        navItems = mutableListOf()
        navItems.apply {
            add(NavItem((R.drawable.ic_add_circle_black_24dp), NAV_ADD_TODO, TodoListAddTodoFragment.newInstance(), true))
        }
    }
}
