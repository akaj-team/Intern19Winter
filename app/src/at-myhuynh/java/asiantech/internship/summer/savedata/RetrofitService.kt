package asiantech.internship.summer.savedata

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    private val baseUrl = "http://5dfb221b38678a00145fa94a.mockapi.io/api/v1"

    companion object {
        fun newInstance(): RetrofitService = RetrofitService().apply {
            Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
    }
}
