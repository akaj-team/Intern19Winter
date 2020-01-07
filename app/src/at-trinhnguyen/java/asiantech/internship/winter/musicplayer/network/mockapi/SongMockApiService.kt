package asiantech.internship.winter.musicplayer.network.mockapi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


private const val BASE_URL = "http://5df73dfb4fdcb20014a47cb8.mockapi.io/"

interface SongMockApiService {
    @GET("musics")
    fun getSong(): Call<List<SongMockApiResponse>>

    @POST("musics")
    fun postSong(@Body songMockApiResponse: SongMockApiResponse): Call<SongMockApiResponse>

    @DELETE("musics/{id}")
    fun deleteSong(@Path("id") id: String): Call<SongMockApiResponse>
}


object SongMockApi {
    val songMockApiService: SongMockApiService by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        retrofit.create(SongMockApiService::class.java)
    }
}
