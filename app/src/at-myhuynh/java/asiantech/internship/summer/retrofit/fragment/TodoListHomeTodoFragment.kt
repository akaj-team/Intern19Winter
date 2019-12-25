package asiantech.internship.summer.retrofit.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import asiantech.internship.summer.R
import asiantech.internship.summer.retrofit.TodoListActivity
import asiantech.internship.summer.retrofit.adapter.TodoListHomeTodoAdapter
import asiantech.internship.summer.retrofit.interfaces.TodoItemOnclick
import asiantech.internship.summer.retrofit.model.Todo
import asiantech.internship.summer.retrofit.viewmodel.TodoViewModel
import kotlinx.android.synthetic.`at-myhuynh`.fragment_todo_list_home_todo.*

class TodoListHomeTodoFragment : Fragment() {

    private lateinit var todoLists: MutableList<Todo>
    private lateinit var homeTodoAdapter: TodoListHomeTodoAdapter
    private lateinit var todoViewModel: TodoViewModel

    companion object {
        fun newInstance() = TodoListHomeTodoFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val imm = (activity as? TodoListActivity)?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow((activity as? TodoListActivity)?.currentFocus?.windowToken, 0)

        todoViewModel = ViewModelProviders.of(requireActivity()).get(TodoViewModel::class.java)
        getAllTodo()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_todo_list_home_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        todoLists = mutableListOf()
        rvHomeTodo.layoutManager = LinearLayoutManager(requireContext())
        homeTodoAdapter = TodoListHomeTodoAdapter(todoLists)
        rvHomeTodo.adapter = homeTodoAdapter
        setOnClickListenerTodoItem(homeTodoAdapter)
    }

    private fun setOnClickListenerTodoItem(adapter: TodoListHomeTodoAdapter) {
        adapter.setTodoItemOnClick(object : TodoItemOnclick {
            override fun editTodoOnClick(todo: Todo) {
                (activity as? TodoListActivity)?.replaceFragment(TodoListAddTodoFragment.newInstance(todo.id, todo.title, todo.status), true)
            }

            override fun deleteTodoOnClick(todo: Todo) {
                val position = todoLists.indexOf(todo)
                todoLists.remove(todo)
                todoViewModel.deleteTodo(todo.id)
                adapter.notifyItemRemoved(position)
            }

            override fun checkBoxTodoOnClick(todo: Todo) {
                todo.status = !todo.status
                updateStatusTodo(todo)
            }
        })
    }

    private fun getAllTodo() {
        todoViewModel.getTodoResponseLiveData().observe(this, Observer<MutableList<Todo>> { t ->
            t?.let {
                todoLists.addAll(t)
            }
            homeTodoAdapter.notifyDataSetChanged()
        })
    }

    private fun updateStatusTodo(todo: Todo) {
        todoViewModel.updateTodo(todo)
    }
}
