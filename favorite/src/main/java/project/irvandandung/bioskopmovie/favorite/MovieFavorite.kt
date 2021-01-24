package project.irvandandung.bioskopmovie.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_movie_favorite.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import project.irvandandung.bioskopmovie.core.ui.MoviePageListAdapter
import project.irvandandung.bioskopmovie.detail.MovieDetailActivity
import project.irvandandung.bioskopmovie.favorite.di.viewModelFavoriteModule

class MovieFavorite : AppCompatActivity() {

    private val movieFavoritePageViewModel: MovieFavoritePageViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_favorite)

        loadKoinModules(viewModelFavoriteModule)

        val moviePageListAdapter = MoviePageListAdapter()

        moviePageListAdapter.onItemClick = { dataMovie ->
            val intent = Intent(this, MovieDetailActivity::class.java)
            intent.putExtra(MovieDetailActivity.EXTRA_DATA, dataMovie)
            startActivity(intent)
        }

        movieFavoritePageViewModel.favoriteMovie.observe(this, { movie ->
            progress_bar.visibility = View.GONE
            moviePageListAdapter.setData(movie)
        })

        with(rv_tourism){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter =moviePageListAdapter
        }
    }
}