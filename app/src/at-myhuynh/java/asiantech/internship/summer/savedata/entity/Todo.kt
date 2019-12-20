package asiantech.internship.summer.savedata.entity

import com.google.gson.annotations.SerializedName

class Todo {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("userId")
    var userId: Int = 0
    @SerializedName("title")
    var title: String = "Non todo"
    @SerializedName("status")
    var status: Boolean = false
}
