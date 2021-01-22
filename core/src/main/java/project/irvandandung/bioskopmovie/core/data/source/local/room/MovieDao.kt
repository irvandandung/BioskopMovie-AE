package project.irvandandung.bioskopmovie.core.data.source.local.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import project.irvandandung.bioskopmovie.core.data.source.local.entity.MovieEntity

@Dao
interface MovieDao{

    @Query("SELECT * FROM movie")
    fun getAllMovie() : Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE is_favorite = 1")
    fun getAllFavoritemovie() : Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListMovie(movieEntity: List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(movie : MovieEntity)
}