package asiantech.internship.summer.savedatabase.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import asiantech.internship.summer.savedatabase.database.DBHandling
import asiantech.internship.summer.savedatabase.database.TodoModel
import kotlinx.android.synthetic.`at-nhatnguyen`.fragment_edit_todo.*

class EditTodoFragment : Fragment() {

    companion object {
        private const val ARG_TODO_NAME = "todoName"
        private const val ARG_TODO_CONTENT = "todoContent"
        private const val ARG_USER_ID = "userId"
        private const val ARG_TODO_ID = "todoId"
        fun newInstance(toDoName: String, todoContent: String, userId: Int, todoId: Int) = EditTodoFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_TODO_NAME, toDoName)
                putString(ARG_TODO_CONTENT, todoContent)
                putInt(ARG_USER_ID, userId)
                putInt(ARG_TODO_ID, todoId)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        edtTodoNew.setText(arguments?.getString(ARG_TODO_NAME))
        edtTodo.setText(arguments?.getString(ARG_TODO_CONTENT))
        val userId = arguments?.getInt(ARG_USER_ID)
        val todoId = arguments?.getInt(ARG_TODO_ID)
        btnDone.setOnClickListener {
            val dbHandling = DBHandling(requireContext())
            val todoName = edtTodoNew.text.toString()
            val todoContent = edtTodo.text.toString()
            val todoModel = TodoModel(todoName, todoContent, userId!!, todoId!!)
            dbHandling.updateTodo(todoModel)
            Toast.makeText(activity, "Success", Toast.LENGTH_SHORT).show()
            fragmentManager?.beginTransaction()?.replace(R.id.frameLayoutActivity, ViewPagerFragment.newInstance(userId))?.commit()
        }
    }
}
