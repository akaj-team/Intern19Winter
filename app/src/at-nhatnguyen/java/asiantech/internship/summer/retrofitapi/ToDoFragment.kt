package asiantech.internship.summer.retrofitapi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import asiantech.internship.summer.R
import asiantech.internship.summer.retrofitapi.retrofit.ApiRequest
import asiantech.internship.summer.retrofitapi.retrofit.RetrofitRequest
import kotlinx.android.synthetic.`at-nhatnguyen`.fragment_list_todo.*
import retrofit2.Callback
import retrofit2.Response

class ToDoFragment : Fragment() {

    companion object {
        fun newInstance() = ToDoFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

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

    private fun generateDataList(list: MutableList<TodoModel>) {
        rlvTodo.layoutManager = LinearLayoutManager(activity)
        val adapter = ListAdapter(list)
        rlvTodo.adapter = adapter
    }
}