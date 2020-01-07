package asiantech.internship.winter.musicplayer.network.webhostapp

import com.google.gson.annotations.SerializedName

data class Payload(

        @field:SerializedName("url")
        val url: String? = null
)
