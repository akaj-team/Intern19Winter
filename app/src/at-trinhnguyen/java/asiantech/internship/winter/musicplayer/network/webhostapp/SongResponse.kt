package asiantech.internship.winter.musicplayer.network.webhostapp

import com.google.gson.annotations.SerializedName

data class SongResponse(

        @field:SerializedName("messages")
        val messages: List<MessagesItem?>? = null
)
