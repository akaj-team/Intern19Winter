package asiantech.internship.summer.savedata.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import asiantech.internship.summer.savedata.Constants.Companion.TAB_LOGIN
import asiantech.internship.summer.savedata.Constants.Companion.TAB_REGISTER
import asiantech.internship.summer.savedata.adapter.TodoListLoginAdapter
import asiantech.internship.summer.savedata.entity.Tab
import kotlinx.android.synthetic.`at-myhuynh`.fragment_todo_list_login.*

class TodoListLoginFragment : Fragment() {

    private lateinit var tabLogins: MutableList<Tab>

    companion object {
        fun newInstance() = TodoListLoginFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_todo_list_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initTabs()
        vpLogin.adapter = TodoListLoginAdapter(childFragmentManager, tabLogins)
        tlLogin.setupWithViewPager(vpLogin)
    }

    private fun initTabs() {
        tabLogins = mutableListOf()
        tabLogins.apply {
            add(Tab(TodoListTabLoginFragment.newInstance(), TAB_LOGIN))
            add(Tab(TodoListTabRegisterFragment.newInstance(), TAB_REGISTER))
        }
    }
}
