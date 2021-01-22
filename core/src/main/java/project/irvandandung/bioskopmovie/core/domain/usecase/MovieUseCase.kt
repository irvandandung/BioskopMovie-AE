package project.irvandandung.bioskopmovie.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import project.irvandandung.bioskopmovie.core.data.Resource
import project.irvandandung.bioskopmovie.core.domain.model.Movie

interface MovieUseCase {
    fun getAllMovie() : Flow<Resource<List<Movie>>>
    fun getFavoriteMovie() : Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state : Boolean)
}