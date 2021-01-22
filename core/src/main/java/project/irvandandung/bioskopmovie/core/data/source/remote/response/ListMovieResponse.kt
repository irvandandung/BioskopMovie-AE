package project.irvandandung.bioskopmovie.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMovieResponse(
    @field:SerializedName("page")
    var page : Int,

    @field:SerializedName("results")
    var results : List<MovieResponse>
)
