package project.irvandandung.bioskopmovie.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.content_movie_detail.*
import org.koin.android.viewmodel.ext.android.viewModel
import project.irvandandung.bioskopmovie.BuildConfig
import project.irvandandung.bioskopmovie.R
import project.irvandandung.bioskopmovie.core.domain.model.Movie

class MovieDetailActivity : AppCompatActivity() {

    private val movieDetailViewModel: MovieDetailViewModel by viewModel()

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        showLoading(true)
        showMovieDetail(detailMovie)
    }

    private fun showMovieDetail(detailMovie: Movie?) {
        detailMovie?.let {
            findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = detailMovie.title
            titleDetail.text = detailMovie.title
            tanggal.text = detailMovie.release_date
            timeDetail.text = detailMovie.popularity.toString()
            vote_average.text = detailMovie.vote_average.toString()
            overview.text = detailMovie.overview
            Glide.with(this)
                .load(BuildConfig.URL_POSTER+detailMovie.poster_path)
                .into(imgBanner)
            showLoading(false)
            var statusFavorite = detailMovie.is_favorite
            setStatusFavorite(statusFavorite)
            findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
                statusFavorite = !statusFavorite
                movieDetailViewModel.setFavoriteMovie(detailMovie, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite){
            fab.setImageResource(R.drawable.ic_baseline_star_rate_24)
        }else{
            fab.setImageResource(R.drawable.ic_no_favorite)
        }
    }

    private fun showLoading(state:Boolean){
        if (state){
            progress_bar.visibility = View.VISIBLE
        }else{
            progress_bar.visibility = View.GONE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}