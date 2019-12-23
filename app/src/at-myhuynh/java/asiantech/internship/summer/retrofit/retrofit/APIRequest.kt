package asiantech.internship.summer.retrofit.retrofit

import asiantech.internship.summer.retrofit.model.Todo
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface APIRequest {

    @GET("todo")
    fun getAllTodo(): Call<MutableList<Todo>>

    @DELETE("todo/{id}")
    fun deleteTodo(@Path("id") id: Int): Call<Todo>
}
