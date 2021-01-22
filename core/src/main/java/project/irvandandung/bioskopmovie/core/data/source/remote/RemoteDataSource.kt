package project.irvandandung.bioskopmovie.core.data.source.remote

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import project.irvandandung.bioskopmovie.core.data.source.remote.network.ApiResponse
import project.irvandandung.bioskopmovie.core.data.source.remote.network.ApiService
import project.irvandandung.bioskopmovie.core.data.source.remote.response.MovieResponse

class RemoteDataSource(private val apiService: ApiService){

    suspend fun getAllMovie() : Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getPopularMovies(page = 1, language = "en-US")
                val dataListMovie = response.results
                if (dataListMovie.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                }else{
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}