package asiantech.internship.summer.savedata.interfaces

import asiantech.internship.summer.savedata.entity.Todo
import retrofit2.Call
import retrofit2.http.*

interface APITodoInterface {

    @GET("todo")
    fun getAllTodoData(): Call<MutableList<Todo>>

    @GET("todo/{id}")
    fun getTodoData(@Path("id") id: Int): Call<Todo>

    @POST("todo")
    fun addTodo(): Call<Todo>

    @PUT("todo/{id}")
    fun updateTodo(@Path("id") id: Int): Call<Todo>

    @DELETE("todo/{id}")
    fun deleteTodo(@Path("id") id: Int): Call<Todo>

}
