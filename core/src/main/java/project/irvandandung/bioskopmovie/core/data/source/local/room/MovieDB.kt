package project.irvandandung.bioskopmovie.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import project.irvandandung.bioskopmovie.core.data.source.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDB : RoomDatabase() {
    abstract fun movieDao() : MovieDao
}