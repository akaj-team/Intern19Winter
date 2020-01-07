package asiantech.internship.winter.musicplayer.network.mockapi

import com.google.gson.annotations.SerializedName

data class SongMockApiResponse(
        @field:SerializedName("id")
        val id: String? = null,

        @field:SerializedName("title")
        val title: String? = null,

        @field:SerializedName("artist")
        val artist: String? = null,

        @field:SerializedName("image")
        val image: String? = null,

        @field:SerializedName("audio")
        val audio: String? = null
)