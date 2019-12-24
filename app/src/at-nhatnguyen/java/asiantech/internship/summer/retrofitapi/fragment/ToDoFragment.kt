package asiantech.internship.summer.retrofitapi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import asiantech.internship.summer.R
import asiantech.internship.summer.retrofitapi.ListAdapter
import asiantech.internship.summer.retrofitapi.TodoModel
import asiantech.internship.summer.retrofitapi.retrofit.ApiRequest
import asiantech.internship.summer.retrofitapi.retrofit.RetrofitRequest
import kotlinx.android.synthetic.`at-nhatnguyen`.fragment_list_todo.*
import retrofit2.Callback
import retrofit2.Response

class ToDoFragment : Fragment() {
    private var idTodo: Int? = 0

    companion object {

        fun newInstance() = ToDoFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        callData()
        floatingActionButton.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.frActivity, NewTodoFragment.newInstance())?.addToBackStack(null)?.commit()
        }
    }


    private fun callData() {
        val service = RetrofitRequest.getRetrofitInstance()?.create(ApiRequest::class.java)
        val call = service?.getTodo()
        call?.enqueue(object : Callback<MutableList<TodoModel>> {
            override fun onFailure(call: retrofit2.Call<MutableList<TodoModel>>, t: Throwable) {
            }

            override fun onResponse(call: retrofit2.Call<MutableList<TodoModel>>, response: Response<MutableList<TodoModel>>) {
                response.body()?.let { generateDataList(it) }
            }
        })
    }

    private fun deleteData() {
        val service = RetrofitRequest.getRetrofitInstance()?.create(ApiRequest::class.java)
        val call = idTodo?.let { service?.delTodo(it) }
        call?.enqueue(object : Callback<TodoModel> {
            override fun onFailure(call: retrofit2.Call<TodoModel>, t: Throwable) {
            }

            override fun onResponse(call: retrofit2.Call<TodoModel>, response: Response<TodoModel>) {
            }
        })
    }

    private fun generateDataList(list: MutableList<TodoModel>) {
        rlvTodo.layoutManager = LinearLayoutManager(activity)
        val adapter = ListAdapter(list)
        rlvTodo.adapter = adapter
        itemOnclick(adapter)
    }

    private fun itemOnclick(adapter: ListAdapter) {
        adapter.click(object : ListAdapter.Onclick {
            override fun iconEditTodoOnclick(todoModel: TodoModel) {
                val todoName = todoModel.todoName.toString()
                val content = todoModel.todoContent.toString()
                val todoId = todoModel.todoId!!.toInt()
                fragmentManager?.beginTransaction()?.replace(R.id.frActivity, EditTodoFragment.newInstance(todoName, content, todoId))?.addToBackStack(null)?.commit()
            }

            override fun iconDeleteOnclick(todoModel: TodoModel) {
                idTodo = todoModel.todoId
                deleteData()
                Toast.makeText(activity, "Delete ${todoModel.todoName}", Toast.LENGTH_LONG).show()
            }
        })
    }
}
