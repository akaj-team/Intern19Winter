package asiantech.internship.summer.retrofitapi.retrofit

import asiantech.internship.summer.retrofitapi.TodoModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiRequest{
    @GET("apiTodo/todo")
    fun getTodo(): Call<MutableList<TodoModel>>
}