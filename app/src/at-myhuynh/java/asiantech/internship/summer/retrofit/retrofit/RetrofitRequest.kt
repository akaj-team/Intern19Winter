package asiantech.internship.summer.retrofit.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitRequest {

    companion object {
        private var retrofit: Retrofit? = null
        private const val BASE_URL = "https://5dfb221b38678a00145fa94a.mockapi.io/api/v1/"
        fun retrofitInstance(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit
        }
    }
}
