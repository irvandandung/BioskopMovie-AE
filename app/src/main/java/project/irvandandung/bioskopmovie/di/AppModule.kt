package project.irvandandung.bioskopmovie.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import project.irvandandung.bioskopmovie.core.domain.usecase.MovieInteractor
import project.irvandandung.bioskopmovie.core.domain.usecase.MovieUseCase
import project.irvandandung.bioskopmovie.detail.MovieDetailViewModel
import project.irvandandung.bioskopmovie.movie_page.MoviePageViewModel

val useCaseModul = module {
    factory<MovieUseCase>{MovieInteractor(get())}
}

val viewModelModule = module {
    viewModel { MoviePageViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
}