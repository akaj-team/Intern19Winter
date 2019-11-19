package asiantech.internship.summer.savedata.fragment

import android.os.Bundle
import android.util.Log
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

class TodoListAddNoteFragment : Fragment() {

    private var mId: Int = -1
    private var mUserId: Int = -1
    private var mTitle: String? = null
    private var mStatus: Int = 0
    private lateinit var todoListData: TodoListDatabaseHelper

    companion object {
        private const val ARG_TODO_ID = "id"
        private const val ARG_TODO_USER_ID = "userID"
        private const val ARG_TODO_TITLE = "title"
        private const val ARG_TODO_STATUS = "status"
        fun newInstance(id: Int = -1, userId: Int = -1, title: String = "", status: Int = 0) = TodoListAddNoteFragment().apply {
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
            mId = it.getInt(ARG_TODO_ID)
            mUserId = it.getInt(ARG_TODO_USER_ID)
            mTitle = it.getString(ARG_TODO_TITLE)
            mStatus = it.getInt(ARG_TODO_STATUS)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_todo_list_add_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        todoListData = TodoListDatabaseHelper(requireContext())
        edtAddNote.setText(mTitle)
        btnAddNote.setOnClickListener {
            val title = edtAddNote.text.toString()
            if (mId == -1) {
                if (title.isEmpty()) {
                    Toast.makeText(requireContext(), "Input Todo", Toast.LENGTH_SHORT).show()
                } else {
                    todoListData.insertTodo(Todo(0, 5, edtAddNote.text.toString()))
                    val todoList = todoListData.readTodos()
                    todoList.forEach {
                        Log.d("xxx", "${it.id} - ${it.title} - ${it.status}")
                    }
                    (activity as? TodoListActivity)?.replaceFragment(TodoListHomeFragment.newInstance(), false)
                }
            } else {
                if (title.isEmpty()) {
                    Toast.makeText(requireContext(), "Input Todo", Toast.LENGTH_SHORT).show()
                } else {
                    val todo = Todo(mId, mUserId, title, mStatus)
                    todoListData.updateTodo(todo)
                    Toast.makeText(requireContext(), "Update", Toast.LENGTH_SHORT).show()
                    (activity as? TodoListActivity)?.replaceFragment(TodoListHomeFragment.newInstance(), false)
                }
            }
        }
    }
}
