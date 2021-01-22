package project.irvandandung.bioskopmovie.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id : Int,

    @ColumnInfo(name = "title")
    var title : String,

    @ColumnInfo(name = "vote_average")
    var vote_average : Double,

    @ColumnInfo(name = "vote_count")
    var vote_count : Int,

    @ColumnInfo(name = "popularity")
    var popularity : Double,

    @ColumnInfo(name = "release_date")
    var release_date : String,

    @ColumnInfo(name = "poster_path")
    var poster_path : String,

    @ColumnInfo(name = "backdrop_path")
    var backdrop_path : String,

    @ColumnInfo(name = "overviervie")
    var overview : String,

    @ColumnInfo(name = "is_favorite")
    var is_favorite : Boolean = false
) : Parcelable