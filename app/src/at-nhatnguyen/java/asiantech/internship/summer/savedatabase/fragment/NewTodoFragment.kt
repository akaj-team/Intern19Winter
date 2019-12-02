package asiantech.internship.summer.savedatabase.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import asiantech.internship.summer.savedatabase.database.DBHandling
import asiantech.internship.summer.savedatabase.database.TodoModel
import kotlinx.android.synthetic.`at-nhatnguyen`.fragment_new_todo.*

class NewTodoFragment : Fragment() {

    companion object {
        const val ARG_USER_ID = "userId"
        fun newInstance(userId: Int) = NewTodoFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_USER_ID, userId)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_new_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val userId = arguments?.getInt(ARG_USER_ID)
        btnDone.setOnClickListener {
            val todoHandling = context?.let { it1 -> DBHandling(it1) }
            val newTodo = edtTodoNew.text.toString()
            val todoContent = edtTodo.text.toString()
            if (newTodo == "" || todoContent == "") {
                Toast.makeText(activity, "Enter full information", Toast.LENGTH_SHORT).show()
            } else {
                todoHandling?.insertTodo(TodoModel(todoName = newTodo, todoContent = todoContent, userId = userId!!, todoId = 0))
                Toast.makeText(activity, "Done", Toast.LENGTH_SHORT).show()
                fragmentManager?.beginTransaction()?.replace(R.id.frameLayoutActivity, ViewPagerFragment.newInstance(userId!!))?.addToBackStack(null)?.commit()
            }
        }
    }
}
