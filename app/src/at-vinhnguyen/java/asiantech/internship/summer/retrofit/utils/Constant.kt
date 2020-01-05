package asiantech.internship.summer.retrofit.utils

import asiantech.internship.summer.BuildConfig

object Constant {
    const val BASE_API_URL = "https://api.themoviedb.org/3/"
    const val BASE_IMG_URL = "https://image.tmdb.org/t/p/w500/"
    const val MOVIE = "movie/"
    const val PERSON = "person/"
    const val API_KEY = BuildConfig.THE_MOVIE_API_KEY

    annotation class APICategory {
        companion object {
            const val TOP_RATED = "top_rated"
            const val NOW_PLAYING = "now_playing"
            const val UPCOMMING = "upcomming"
            const val POPULAR = "popular"
        }
    }
}