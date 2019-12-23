package asiantech.internship.summer.retrofit.retrofit

import asiantech.internship.summer.retrofit.model.Todo
import retrofit2.Call
import retrofit2.http.GET

interface APIRequest {

    @GET("todo")
    fun getAllTodo(): Call<MutableList<Todo>>
}
