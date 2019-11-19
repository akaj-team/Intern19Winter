package asiantech.internship.summer.savedata.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import asiantech.internship.summer.savedata.TodoListActivity
import asiantech.internship.summer.savedata.data.TodoListDatabaseHelper
import kotlinx.android.synthetic.`at-myhuynh`.fragment_todo_list_tab_login.*

class TodoListTabLoginFragment : Fragment() {
    private lateinit var userDatabase: TodoListDatabaseHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_todo_list_tab_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        userDatabase = TodoListDatabaseHelper(requireContext())
        btnLogin.setOnClickListener {
            if (login(edtUserName.text.toString(), edtPassword.text.toString()) != -1) {
                (activity as? TodoListActivity)?.replaceFragment(TodoListHomeFragment.newInstance(), false)
            } else {
                Toast.makeText(requireContext(), "Login Fail", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun login(userName: String, password: String): Int {
        var id = -1
        val users = userDatabase.readUserLogin(userName, password)
        if (users.size > 0 && users[0].id != -1) {
            id = users[0].id
            Toast.makeText(requireContext(), "${users[0].id} - ${users[0].userName}", Toast.LENGTH_SHORT).show()
        }
        return id
    }
}
