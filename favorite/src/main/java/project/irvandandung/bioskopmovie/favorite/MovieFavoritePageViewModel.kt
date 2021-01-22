package project.irvandandung.bioskopmovie.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import project.irvandandung.bioskopmovie.core.domain.usecase.MovieUseCase

class MovieFavoritePageViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val favoriteMovie= movieUseCase.getFavoriteMovie().asLiveData()
}