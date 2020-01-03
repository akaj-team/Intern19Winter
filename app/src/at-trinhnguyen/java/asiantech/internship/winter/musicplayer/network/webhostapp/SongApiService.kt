package asiantech.internship.winter.musicplayer.network.webhostapp

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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

object SongApi {
    val songApiService: SongApiService by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        retrofit.create(SongApiService::class.java)
    }
}
