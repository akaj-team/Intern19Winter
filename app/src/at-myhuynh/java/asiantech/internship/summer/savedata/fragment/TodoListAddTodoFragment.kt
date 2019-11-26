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
import asiantech.internship.summer.savedata.entity.Todo
import kotlinx.android.synthetic.`at-myhuynh`.fragment_todo_list_add_note.*

class TodoListAddTodoFragment : Fragment() {

    private var todoId: Int = -1
    private var userId: Int = -1
    private var title: String? = null
    private var status: Int = 0
    private lateinit var todoListData: TodoListDatabaseHelper

    companion object {
        private const val ARG_TODO_ID = "id"
        private const val ARG_TODO_USER_ID = "userID"
        private const val ARG_TODO_TITLE = "title"
        private const val ARG_TODO_STATUS = "status"
        fun newInstance(id: Int = -1, userId: Int, title: String = "", status: Int = 0) = TodoListAddTodoFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_TODO_ID, id)
                putInt(ARG_TODO_USER_ID, userId)
                putString(ARG_TODO_TITLE, title)
                putInt(ARG_TODO_STATUS, status)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            todoId = it.getInt(ARG_TODO_ID)
            userId = it.getInt(ARG_TODO_USER_ID)
            title = it.getString(ARG_TODO_TITLE)
            status = it.getInt(ARG_TODO_STATUS)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_todo_list_add_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        todoListData = TodoListDatabaseHelper(requireContext())
        edtAddNote.setText(title)

        Toast.makeText(requireContext(), "Add: $userId", Toast.LENGTH_SHORT).show()
        btnAddNote.setOnClickListener {
            val title = edtAddNote.text.toString()
            if (todoId == -1) {
                if (title.isEmpty()) {
                    Toast.makeText(requireContext(), "Input Todo", Toast.LENGTH_SHORT).show()
                } else {
                    todoListData.insertTodo(Todo(0, userId, edtAddNote.text.toString()))
                    (activity as? TodoListActivity)?.removeFragmentInBackStack(TodoListHomeFragment.newInstance(userId))
                    (activity as? TodoListActivity)?.replaceFragment(TodoListHomeFragment.newInstance(userId), false)
                }
            } else {
                if (title.isEmpty()) {
                    Toast.makeText(requireContext(), "Input Todo", Toast.LENGTH_SHORT).show()
                } else {
                    val todo = Todo(todoId, userId, title, status)
                    todoListData.updateTodo(todo)
                    Toast.makeText(requireContext(), "Update", Toast.LENGTH_SHORT).show()
                    (activity as? TodoListActivity)?.removeFragmentInBackStack(TodoListHomeFragment.newInstance(userId))
                    (activity as? TodoListActivity)?.replaceFragment(TodoListHomeFragment.newInstance(userId), false)
                }
            }
        }
    }
}
