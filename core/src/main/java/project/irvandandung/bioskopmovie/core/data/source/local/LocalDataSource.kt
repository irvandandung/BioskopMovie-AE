package project.irvandandung.bioskopmovie.core.data.source.local

import kotlinx.coroutines.flow.Flow
import project.irvandandung.bioskopmovie.core.data.source.local.entity.MovieEntity
import project.irvandandung.bioskopmovie.core.data.source.local.room.MovieDao

class LocalDataSource(private val movieDao: MovieDao){

    fun getAllMovie(): Flow<List<MovieEntity>> = movieDao.getAllMovie()

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getAllFavoritemovie()

    suspend fun insertListMovie(movieList : List<MovieEntity>) = movieDao.insertListMovie(movieList)

    fun setFavoriteMovie(movie : MovieEntity, state : Boolean){
        movie.is_favorite = state
        movieDao.updateFavoriteMovie(movie)
    }
}