package asiantech.internship.summer.retrofit.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import asiantech.internship.summer.R
import asiantech.internship.summer.retrofit.TodoListActivity
import asiantech.internship.summer.retrofit.model.Todo
import asiantech.internship.summer.retrofit.viewmodel.TodoViewModel
import kotlinx.android.synthetic.`at-myhuynh`.fragment_todo_list_add_note.*

class TodoListAddTodoFragment : Fragment() {

    private var todoId: Int = -1
    private var title: String? = null
    private var status: Boolean = false
    private lateinit var todoViewModel: TodoViewModel

    companion object {
        private const val ARG_TODO_ID = "id"
        private const val ARG_TODO_TITLE = "title"
        private const val ARG_TODO_STATUS = "status"
        fun newInstance(id: Int = -1, title: String = "", status: Boolean = false) = TodoListAddTodoFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_TODO_ID, id)
                putString(ARG_TODO_TITLE, title)
                putBoolean(ARG_TODO_STATUS, status)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            todoId = it.getInt(ARG_TODO_ID)
            title = it.getString(ARG_TODO_TITLE)
            status = it.getBoolean(ARG_TODO_STATUS)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_todo_list_add_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        edtAddNote.setText(title)

        btnAddNote.setOnClickListener {
            val title = edtAddNote.text.toString()
            if (todoId == -1) {
                if (title.isEmpty()) {
                    Toast.makeText(requireContext(), "Input Todo", Toast.LENGTH_SHORT).show()
                } else {
                    val todo = Todo().apply { this.title = title }
                    createNewTodo(todo)
                    (activity as? TodoListActivity)?.removeFragmentInBackStack(TodoListHomeFragment.newInstance())
                    (activity as? TodoListActivity)?.replaceFragment(TodoListHomeFragment.newInstance(), false)
                }
            } else {
                if (title.isEmpty()) {
                    Toast.makeText(requireContext(), "Input Todo", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Update", Toast.LENGTH_SHORT).show()
                    (activity as? TodoListActivity)?.removeFragmentInBackStack(TodoListHomeFragment.newInstance())
                    (activity as? TodoListActivity)?.replaceFragment(TodoListHomeFragment.newInstance(), false)
                }
            }
        }
        todoViewModel = ViewModelProviders.of(requireActivity()).get(TodoViewModel::class.java)
    }

    private fun createNewTodo(todo: Todo) {
        todoViewModel.createNewTodo(todo)
    }
}
