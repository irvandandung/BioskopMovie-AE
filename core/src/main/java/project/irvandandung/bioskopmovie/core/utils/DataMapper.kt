package project.irvandandung.bioskopmovie.core.utils

import project.irvandandung.bioskopmovie.core.data.source.local.entity.MovieEntity
import project.irvandandung.bioskopmovie.core.data.source.remote.response.MovieResponse
import project.irvandandung.bioskopmovie.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input : List<MovieResponse>) :List<MovieEntity>{
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                title = it.title,
                overview = it.overview,
                poster_path = it.poster_path,
                popularity = it.popularity,
                vote_count = it.vote_count,
                vote_average = it.vote_average,
                release_date = it.release_date,
                backdrop_path = it.backdrop_path
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>) : List<Movie> =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                overview = it.overview,
                poster_path = it.poster_path,
                popularity = it.popularity,
                vote_count = it.vote_count,
                vote_average = it.vote_average,
                release_date = it.release_date,
                backdrop_path = it.backdrop_path,
                is_favorite = it.is_favorite
            )
        }

    fun mapDomainToEntity(input: Movie) : MovieEntity =
        MovieEntity(
            id = input.id,
            title = input.title,
            overview = input.overview,
            poster_path = input.poster_path,
            popularity = input.popularity,
            vote_count = input.vote_count,
            vote_average = input.vote_average,
            release_date = input.release_date,
            backdrop_path = input.backdrop_path,
            is_favorite = input.is_favorite
        )
}