package project.irvandandung.bioskopmovie.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    var id : Int,
    var title : String,
    var vote_average : Double,
    var vote_count : Int,
    var popularity : Double,
    var release_date : String,
    var poster_path : String,
    var backdrop_path : String,
    var overview : String,
    var is_favorite : Boolean
):Parcelable
