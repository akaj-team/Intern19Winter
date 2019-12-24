package asiantech.internship.summer.retrofitapi.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import asiantech.internship.summer.retrofitapi.TodoModel
import asiantech.internship.summer.retrofitapi.retrofit.ApiRequest
import asiantech.internship.summer.retrofitapi.retrofit.RetrofitRequest
import kotlinx.android.synthetic.`at-nhatnguyen`.fragment_add_todo.*
import retrofit2.Callback
import retrofit2.Response

class NewTodoFragment : Fragment() {

    companion object {
        fun newInstance() = NewTodoFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btnDone.setOnClickListener {

            val todoName = edtTodoName.text.toString()
            val content = edtContent.text.toString()

            Log.d("xxx", todoName)
            Log.d("xxx", content)
            val service = RetrofitRequest.getRetrofitInstance()?.create(ApiRequest::class.java)
            val call = service?.addTodo(TodoModel(todoName, content, 0))
            call?.enqueue(object : Callback<TodoModel> {
                override fun onFailure(call: retrofit2.Call<TodoModel>, t: Throwable) {
                }

                override fun onResponse(call: retrofit2.Call<TodoModel>, response: Response<TodoModel>) {
                    Log.d("xxx", response.body().toString())
                }
            })
            fragmentManager?.beginTransaction()?.replace(R.id.frActivity, ToDoFragment.newInstance())?.commit()
        }
    }
}