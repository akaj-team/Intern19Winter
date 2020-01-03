package asiantech.internship.summer.retrofit.data.source.remote


import asiantech.internship.summer.BuildConfig
import asiantech.internship.summer.retrofit.data.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceAPI {

    @GET("movie/{category}?api_key=" + BuildConfig.THE_MOVIE_API_KEY)
    fun getListMovieByCategory(@Path("category") category: String, @Query("page") page: Int): Call<MovieResponse>

    @GET("movie/{id}?api_key=" + BuildConfig.THE_MOVIE_API_KEY)
    fun getMovieDetail(@Path("id") id: String?, @Query("append_to_response") type: String?): Call<List<MovieResponse>>

    @GET("search/movie?api_key=" + BuildConfig.THE_MOVIE_API_KEY)
    fun getListMovieBySearch(@Query("query") query: String?, @Query("page") page: Int): Call<List<MovieResponse>>

    @GET("search/movie?api_key=" + BuildConfig.THE_MOVIE_API_KEY)
    fun getMovieByGenres(@Query("query") query: String?, @Query("page") page: Int): Call<List<MovieResponse>>

    @GET("movie/{id}/videos?api_key=" + BuildConfig.THE_MOVIE_API_KEY)
    fun getVideoMovie(@Path("id") id: String?): Call<MovieResponse>
}