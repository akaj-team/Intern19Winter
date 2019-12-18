package asiantech.internship.winter.musicplayer.network.mockapi

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

private const val BASE_URL = "https://5df73dfb4fdcb20014a47cb8.mockapi.io/"

interface SongMockApiService {
    @GET("musics")
    fun getSong(): Call<List<SongMockApiResponse>>

    @POST("musics")
    fun postSong(@Body songMockApiResponse: SongMockApiResponse): Call<SongMockApiResponse>
}

private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

object SongMockApi {
    val songMockApiService: SongMockApiService by lazy {
        retrofit.create(SongMockApiService::class.java)
    }
}
