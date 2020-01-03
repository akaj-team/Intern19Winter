package asiantech.internship.summer.retrofit.data.source.remote

import asiantech.internship.summer.retrofit.utils.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ServiceClient {
    fun createServiceClient(): ServiceAPI {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val build: Retrofit = Retrofit.Builder().baseUrl(Constant.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        return build.create(ServiceAPI::class.java)
    }
}
