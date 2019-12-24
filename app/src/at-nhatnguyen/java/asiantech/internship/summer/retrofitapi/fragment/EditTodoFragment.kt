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
import kotlinx.android.synthetic.`at-nhatnguyen`.fragment_edit_todo.*
import retrofit2.Callback
import retrofit2.Response

class EditTodoFragment : Fragment() {

    companion object {
        private const val ARG_TODO_NAME = "todoName"
        private const val ARG_TODO_CONTENT = "todoContent"
        private const val ARG_TODO_ID = "todoID"
        fun newInstance(todoName: String, todoContent: String, id: Int) = EditTodoFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_TODO_NAME, todoName)
                putString(ARG_TODO_CONTENT, todoContent)
                putInt(ARG_TODO_ID, id)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val name = arguments?.getString(ARG_TODO_NAME)
        val content = arguments?.getString(ARG_TODO_CONTENT)
        val id = arguments?.getInt(ARG_TODO_ID)!!.toInt()
        edtTodoName.setText(name)
        edtContent.setText(content)

        btnDone.setOnClickListener {
            val todoName = edtTodoName.text.toString()
            val todoContent = edtContent.text.toString()
            val service = RetrofitRequest.getRetrofitInstance()?.create(ApiRequest::class.java)
            val call = service?.updateTodo(id, TodoModel(todoName, todoContent, 0))
            call?.enqueue(object : Callback<TodoModel> {
                override fun onFailure(call: retrofit2.Call<TodoModel>, t: Throwable) {
                }

                override fun onResponse(call: retrofit2.Call<TodoModel>, response: Response<TodoModel>) {
                    Log.d("xxx", response.body().toString())
                    Log.d("xxx","$id")
                }
            })

            fragmentManager?.beginTransaction()?.replace(R.id.frActivity, ToDoFragment.newInstance())?.commit()
        }
    }
}