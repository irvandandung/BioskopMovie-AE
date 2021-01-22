package project.irvandandung.bioskopmovie.detail

import androidx.lifecycle.ViewModel
import project.irvandandung.bioskopmovie.core.domain.model.Movie
import project.irvandandung.bioskopmovie.core.domain.usecase.MovieUseCase

class MovieDetailViewModel(private val movieUseCase: MovieUseCase): ViewModel() {
    fun setFavoriteMovie(movie: Movie, state:Boolean) = movieUseCase.setFavoriteMovie(movie, state)
}