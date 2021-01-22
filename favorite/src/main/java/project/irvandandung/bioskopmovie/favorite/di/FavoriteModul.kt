package project.irvandandung.bioskopmovie.favorite.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import project.irvandandung.bioskopmovie.favorite.MovieFavoritePageViewModel

val viewModelFavoriteModule = module {
    viewModel {  MovieFavoritePageViewModel(get()) }
}