package asiantech.internship.summer.retrofit.data.model

import com.google.gson.annotations.SerializedName

data class Movie(@field:SerializedName("id") var ID: String,
                 @field:SerializedName("original_title") var nameMovie: String,
                 @field:SerializedName("vote_average") var rating: String,
                 @field:SerializedName("poster_path") var poster: String,
                 @field:SerializedName("release_date") var date: String,
                 @field:SerializedName("overview") var content: String)