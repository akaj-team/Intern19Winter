package asiantech.internship.summer.savedata.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import asiantech.internship.summer.R
import asiantech.internship.summer.savedata.TodoListActivity
import asiantech.internship.summer.savedata.adapter.TodoListHomeTodoAdapter
import asiantech.internship.summer.savedata.entity.Todo
import asiantech.internship.summer.savedata.interfaces.TodoItemOnclick
import kotlinx.android.synthetic.`at-myhuynh`.fragment_todo_list_home_todo.*

class TodoListHomeTodoFragment : Fragment() {

    companion object {
        private var mTodoLists = mutableListOf<Todo>()
        fun newInstance(todoList: MutableList<Todo>) = TodoListHomeTodoFragment().apply {
            mTodoLists = todoList
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_todo_list_home_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rvHomeTodo.layoutManager = LinearLayoutManager(requireContext())
        val homeTodoAdapter = TodoListHomeTodoAdapter(mTodoLists)
        rvHomeTodo.adapter = homeTodoAdapter
        setOnClickListenerTodoItem(homeTodoAdapter)
    }

    private fun setOnClickListenerTodoItem(adapter: TodoListHomeTodoAdapter) {
        adapter.setTodoItemOnClick(object : TodoItemOnclick {
            override fun editTodoOnClick(todo: Todo) {
                (activity as? TodoListActivity)?.replaceFragment(TodoListAddNoteFragment.newInstance(todo.title), true)
            }

            override fun deleteTodoOnClick(todo: Todo) {
                Toast.makeText(requireContext(), "Deleted ${todo.title}", Toast.LENGTH_SHORT).show()
            }

        })
    }
}
