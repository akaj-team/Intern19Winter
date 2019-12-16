package asiantech.internship.winter.musicplayer.network

import com.google.gson.annotations.SerializedName

data class Attachment(

        @field:SerializedName("payload")
        val payload: Payload? = null,

        @field:SerializedName("type")
        val type: String? = null
)
