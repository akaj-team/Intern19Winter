package asiantech.internship.summer.savedata.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import asiantech.internship.summer.savedata.adapter.TodoListLoginAdapter
import kotlinx.android.synthetic.`at-myhuynh`.fragment_todo_list_login.*

class TodoListLoginFragment : Fragment() {

    companion object {
        fun newInstance() = TodoListLoginFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_todo_list_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vpLogin.adapter = TodoListLoginAdapter(childFragmentManager)
        tlLogin.setupWithViewPager(vpLogin)
    }
}
