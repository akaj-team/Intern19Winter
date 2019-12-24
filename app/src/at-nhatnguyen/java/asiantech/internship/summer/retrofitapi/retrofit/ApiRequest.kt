package asiantech.internship.summer.retrofitapi.retrofit

import asiantech.internship.summer.retrofitapi.TodoModel
import retrofit2.Call
import retrofit2.http.*

interface ApiRequest {
    @GET("apiTodo/todo")
    fun getTodo(): Call<MutableList<TodoModel>>

    @DELETE("apiTodo/todo/{id}")
    fun delTodo(@Path("id") id: Int): Call<TodoModel>

    @POST("apiTodo/todo")
    fun addTodo(@Body todoModel: TodoModel): Call<TodoModel>

    @PUT("apiTodo/todo/{id}")
    fun updateTodo(@Path("id") id: Int, @Body todoModel: TodoModel): Call<TodoModel>
}
