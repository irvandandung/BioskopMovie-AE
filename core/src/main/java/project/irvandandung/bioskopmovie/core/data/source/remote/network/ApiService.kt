package project.irvandandung.bioskopmovie.core.data.source.remote.network

import project.irvandandung.bioskopmovie.core.BuildConfig
import project.irvandandung.bioskopmovie.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    //Movies
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apikey: String = BuildConfig.TSDB_API_KEY,
        @Query("language") language: String,
        @Query("page") page: Int
    ): ListMovieResponse
}