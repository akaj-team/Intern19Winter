package asiantech.internship.summer.savedata.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import asiantech.internship.summer.R
import asiantech.internship.summer.savedata.TodoListActivity
import asiantech.internship.summer.savedata.adapter.TodoListHomeTodoAdapter
import asiantech.internship.summer.savedata.entity.Todo
import asiantech.internship.summer.savedata.interfaces.APITodoInterface
import asiantech.internship.summer.savedata.interfaces.TodoItemOnclick
import kotlinx.android.synthetic.`at-myhuynh`.fragment_todo_list_home_todo.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TodoListHomeTodoFragment : Fragment() {

    private lateinit var todoLists: MutableList<Todo>
    private val baseUrl = "https://5dfb221b38678a00145fa94a.mockapi.io/api/v1/"
    private lateinit var homeTodoAdapter: TodoListHomeTodoAdapter

    companion object {
        fun newInstance() = TodoListHomeTodoFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(APITodoInterface::class.java)
        val call = service.getAllTodoData()
        call.enqueue(object : Callback<MutableList<Todo>> {
            override fun onFailure(call: Call<MutableList<Todo>>, t: Throwable) {
                Log.d("xxx", "onFailure -> ${t.message}")
            }

            override fun onResponse(call: Call<MutableList<Todo>>, response: Response<MutableList<Todo>>) {
                if (response.code() == 200) {
                    val response = response.body()
                    response?.forEach {
                        Log.d("xxx", "${it.id} - ${it.title} - ${it.status}")
                    }
                    response?.let {
                        todoLists = it
                    }
                }
            }
        })
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
        rvHomeTodo.layoutManager = LinearLayoutManager(requireContext())
        todoLists = mutableListOf()

        Log.d("xxx", todoLists.size.toString())

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
                todoLists.remove(todo)
                adapter.notifyDataSetChanged()
            }

            override fun checkBoxTodoOnClick(todo: Todo) {
                todo.status = !todo.status
            }
        })
    }
}
