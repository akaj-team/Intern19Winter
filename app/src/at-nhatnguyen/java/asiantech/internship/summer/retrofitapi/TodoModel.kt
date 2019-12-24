package asiantech.internship.summer.retrofitapi

import com.google.gson.annotations.SerializedName

data class TodoModel(
        @SerializedName("todoName")
        var todoName: String? = null,

        @SerializedName("todoContent")
        var todoContent: String? = null,

        @SerializedName("id")
        var todoId: Int? = null
)
