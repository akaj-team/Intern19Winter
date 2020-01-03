package asiantech.internship.summer.retrofit.screen

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import asiantech.internship.summer.R
import asiantech.internship.summer.retrofit.data.model.Movie
import asiantech.internship.summer.retrofit.data.model.MovieResponse
import asiantech.internship.summer.retrofit.data.model.ResultsItem
import asiantech.internship.summer.retrofit.data.source.remote.ServiceAPI
import asiantech.internship.summer.retrofit.data.source.remote.ServiceClient
import asiantech.internship.summer.retrofit.utils.Constant.APICategory.Companion.NOW_PLAYING
import asiantech.internship.summer.retrofit.utils.Constant.APICategory.Companion.UPCOMMING
import kotlinx.android.synthetic.`at-vinhnguyen`.activity_themovie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TheMovieActivity : AppCompatActivity() {

    lateinit var serviceAPI: ServiceAPI


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_themovie)
        initView()
    }

    private fun initView() {
        recyclerViewMovie.setHasFixedSize(true)
        serviceAPI = ServiceClient.createServiceClient()
        var listMovie: List<Movie>? = null
        var call = serviceAPI.getListMovieByCategory(NOW_PLAYING, 1)

        call.enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.i("TestAPI", t.message)
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                response.body()?.let {
                    var listResult = it.results
                    var theMovieAdapter = TheMovieAdapter(listResult as List<ResultsItem>, this@TheMovieActivity)
                    listResult.forEach { Log.i("TestAPI",it.title) }
                    recyclerViewMovie.adapter = theMovieAdapter

                }
            }

        })
    }
}