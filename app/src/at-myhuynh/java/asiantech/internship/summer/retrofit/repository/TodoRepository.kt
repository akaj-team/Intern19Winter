package asiantech.internship.summer.retrofit.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import asiantech.internship.summer.retrofit.model.Todo
import asiantech.internship.summer.retrofit.retrofit.APIRequest
import asiantech.internship.summer.retrofit.retrofit.RetrofitRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoRepository {
    private var apiRequest: APIRequest? = null

    companion object {
        fun newInstance() = TodoRepository().apply {
            apiRequest = RetrofitRequest.retrofitInstance()?.create(APIRequest::class.java)
        }
    }

    fun getAllTodo(): LiveData<MutableList<Todo>> {
        val data = MutableLiveData<MutableList<Todo>>()
        apiRequest?.getAllTodo()?.enqueue(object : Callback<MutableList<Todo>> {
            override fun onFailure(call: Call<MutableList<Todo>>, t: Throwable) {
                Log.d("xxx", "Fail: ${t.message}")
            }

            override fun onResponse(call: Call<MutableList<Todo>>, response: Response<MutableList<Todo>>) {
                response.body()?.let {
                    data.value = response.body()
                }
            }

        })

        return data
    }

    fun deleteTodo(id: Int): Todo? {
        var todoDelete: Todo? = null
        apiRequest?.deleteTodo(id)?.enqueue(object : Callback<Todo> {
            override fun onFailure(call: Call<Todo>, t: Throwable) {
                Log.d("xxx", "Fail: ${t.message}")
            }

            override fun onResponse(call: Call<Todo>, response: Response<Todo>) {
                response.body()?.let {
                    todoDelete = it
                }
            }

        })
        return todoDelete
    }
}
