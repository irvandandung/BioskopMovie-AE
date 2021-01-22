package project.irvandandung.bioskopmovie.movie_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import project.irvandandung.bioskopmovie.core.domain.usecase.MovieUseCase

class MoviePageViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    val movie = movieUseCase.getAllMovie().asLiveData()

}