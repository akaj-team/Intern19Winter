package asiantech.internship.summer.retrofit.retrofit

import asiantech.internship.summer.retrofit.model.Todo
import retrofit2.Call
import retrofit2.http.*

interface APIRequest {

    @GET("todo")
    fun getAllTodo(): Call<MutableList<Todo>>

    @POST("todo")
    fun createNewTodo(@Body todo: Todo): Call<Todo>

    @PUT("todo/{id}")
    fun updateTodo(@Path("id") int: Int, @Body todo: Todo): Call<Todo>

    @DELETE("todo/{id}")
    fun deleteTodo(@Path("id") id: Int): Call<Todo>

}
