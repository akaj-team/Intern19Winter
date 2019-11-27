package asiantech.internship.summer.savedata.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import asiantech.internship.summer.R
import asiantech.internship.summer.savedata.TodoListActivity
import asiantech.internship.summer.savedata.adapter.TodoListHomeTodoAdapter
import asiantech.internship.summer.savedata.data.TodoListDatabaseHelper
import asiantech.internship.summer.savedata.entity.Todo
import asiantech.internship.summer.savedata.interfaces.TodoItemOnclick
import kotlinx.android.synthetic.`at-myhuynh`.fragment_todo_list_home_todo.*

class TodoListHomeTodoFragment : Fragment() {

    private lateinit var todoListDatabase: TodoListDatabaseHelper
    private lateinit var todoLists: MutableList<Todo>
    private var userId = -1

    companion object {
        private const val ARG_USER_ID = "userId"
        fun newInstance(userId: Int) = TodoListHomeTodoFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_USER_ID, userId)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userId = it.getInt(ARG_USER_ID)
        }

        todoListDatabase = TodoListDatabaseHelper(requireContext())
        todoLists = todoListDatabase.readTodos(userId)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val imm = (activity as? TodoListActivity)?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow((activity as? TodoListActivity)?.currentFocus?.windowToken, 0)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_todo_list_home_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        todoListDatabase = TodoListDatabaseHelper(requireContext())
        rvHomeTodo.layoutManager = LinearLayoutManager(requireContext())
        val homeTodoAdapter = TodoListHomeTodoAdapter(todoLists)
        rvHomeTodo.adapter = homeTodoAdapter
        setOnClickListenerTodoItem(homeTodoAdapter)
    }

    private fun setOnClickListenerTodoItem(adapter: TodoListHomeTodoAdapter) {
        adapter.setTodoItemOnClick(object : TodoItemOnclick {
            override fun editTodoOnClick(todo: Todo) {
                (activity as? TodoListActivity)?.replaceFragment(TodoListAddTodoFragment.newInstance(todo.id, todo.userId, todo.title, todo.status), true)
            }

            override fun deleteTodoOnClick(todo: Todo) {
                val delete = todoListDatabase.deleteTodo(todo)
                todoLists.remove(todo)
                adapter.notifyDataSetChanged()
                Toast.makeText(requireContext(), "Deleted $delete", Toast.LENGTH_SHORT).show()
            }

            override fun checkBoxTodoOnClick(todo: Todo) {
                if (todo.status == 0) {
                    todo.status = 1
                } else todo.status = 0

                val update = todoListDatabase.updateTodo(todo)
                todoLists = todoListDatabase.readTodos(todo.userId)

                todoListDatabase.readTodos(todo.userId).forEach {
                    Log.d("zzz", "${it.id} - ${it.userId} - ${it.title} - ${it.status}")
                }
                Toast.makeText(requireContext(), "Update $update", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
