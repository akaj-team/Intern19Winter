package asiantech.internship.summer.savedata.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import kotlinx.android.synthetic.`at-myhuynh`.row_item_todo.*

class TodoListHomeTodoFragment : Fragment() {

    private lateinit var todoListDatabase: TodoListDatabaseHelper

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
        todoListDatabase = TodoListDatabaseHelper(requireContext())
        rvHomeTodo.layoutManager = LinearLayoutManager(requireContext())
        val homeTodoAdapter = TodoListHomeTodoAdapter(mTodoLists)
        rvHomeTodo.adapter = homeTodoAdapter
        setOnClickListenerTodoItem(homeTodoAdapter)
    }

    private fun setOnClickListenerTodoItem(adapter: TodoListHomeTodoAdapter) {
        adapter.setTodoItemOnClick(object : TodoItemOnclick {
            override fun editTodoOnClick(todo: Todo) {
                (activity as? TodoListActivity)?.replaceFragment(TodoListAddNoteFragment.newInstance(todo.id, todo.idUser, todo.title, todo.status), true)
            }

            override fun deleteTodoOnClick(todo: Todo) {
                val delete = todoListDatabase.deleteTodo(todo)
                mTodoLists.remove(todo)
                adapter.notifyDataSetChanged()
                Toast.makeText(requireContext(), "Deleted $delete", Toast.LENGTH_SHORT).show()
            }

            override fun checkBoxTodoOnClick(todo: Todo) {
                if (todo.status == 0) {
                    todo.status = 1
                } else todo.status = 0

                val update = todoListDatabase.updateTodo(todo)
                mTodoLists = todoListDatabase.readTodos()

                todoListDatabase.readTodos().forEach {
                    Log.d("zzz", "${it.id} - ${it.idUser} - ${it.title} - ${it.status}")
                }
                Toast.makeText(requireContext(), "Update $update", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
