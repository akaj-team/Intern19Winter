package asiantech.internship.winter.musicplayer.network

import com.google.gson.annotations.SerializedName

data class MessagesItem(

        @field:SerializedName("attachment")
        val attachment: Attachment? = null,

        @field:SerializedName("text")
        val text: String? = null
)
