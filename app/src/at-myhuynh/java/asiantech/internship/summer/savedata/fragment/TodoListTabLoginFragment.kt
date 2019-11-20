package asiantech.internship.summer.savedata.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import asiantech.internship.summer.savedata.Constants.Companion.KEY_USER_ID
import asiantech.internship.summer.savedata.TodoListActivity
import asiantech.internship.summer.savedata.data.TodoListDatabaseHelper
import asiantech.internship.summer.savedata.entity.User
import kotlinx.android.synthetic.`at-myhuynh`.fragment_todo_list_tab_login.*

class TodoListTabLoginFragment : Fragment() {
    private lateinit var userDatabase: TodoListDatabaseHelper
    private lateinit var preferences: SharedPreferences
    private val sharedFile = "userLogin"

    companion object {
        private const val USER_ID_DEFAULT = -1
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_todo_list_tab_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        preferences = requireContext().getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
        val userId = readSharedPreferences()
        if (userId != USER_ID_DEFAULT) {
            (activity as? TodoListActivity)?.replaceFragment(TodoListHomeFragment.newInstance(userId), false)
        } else {
            btnLogin.setOnClickListener {
                userDatabase = TodoListDatabaseHelper(requireContext())
                val user = login(edtUserName.text.toString(), edtPassword.text.toString())
                if (user != null) {
                    val preferencesEditor = preferences.edit()
                    preferencesEditor.putInt(KEY_USER_ID, user.id)
                    preferencesEditor.apply()
                    (activity as? TodoListActivity)?.replaceFragment(TodoListHomeFragment.newInstance(id), false)
                } else {
                    Toast.makeText(requireContext(), "Login Fail", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun readSharedPreferences() = preferences.getInt(KEY_USER_ID, USER_ID_DEFAULT)

    private fun login(userName: String, password: String): User? {
        val users = userDatabase.readUserLogin(userName, password)
        if (users.size > 0 && users[0].id != -1) {
            Toast.makeText(requireContext(), "${users[0].id} - ${users[0].userName}", Toast.LENGTH_SHORT).show()
            return users[0]
        }
        return null
    }
}
