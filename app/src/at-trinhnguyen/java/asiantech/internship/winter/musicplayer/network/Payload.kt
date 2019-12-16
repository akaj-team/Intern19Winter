package asiantech.internship.winter.musicplayer.network

import com.google.gson.annotations.SerializedName

data class Payload(

        @field:SerializedName("url")
        val url: String? = null
)
