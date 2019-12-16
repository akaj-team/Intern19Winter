package asiantech.internship.winter.musicplayer.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "http://lulzquan01.000webhostapp.com/"

interface SongApiService {
    @GET("mp3.php")
    fun getSong(@Query("baihat") songName: String): Call<SongResponse>
}

private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

object SongApi {
    val songApiService: SongApiService by lazy {
        retrofit.create(SongApiService::class.java)
    }
}
