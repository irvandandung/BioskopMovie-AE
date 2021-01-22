package project.irvandandung.bioskopmovie.movie_page

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_movie_page.*
import kotlinx.android.synthetic.main.view_error.view.*
import org.koin.android.viewmodel.ext.android.viewModel
import project.irvandandung.bioskopmovie.R
import project.irvandandung.bioskopmovie.core.data.Resource
import project.irvandandung.bioskopmovie.core.ui.MoviePageListAdapter
import project.irvandandung.bioskopmovie.detail.MovieDetailActivity

class MoviePageFragment : Fragment() {

    private val moviePageViewModel: MoviePageViewModel by viewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            val moviePageListAdapter= MoviePageListAdapter()

            moviePageListAdapter.onItemClick = { dataMovie ->
                val intent = Intent(activity, MovieDetailActivity::class.java)
                intent.putExtra(MovieDetailActivity.EXTRA_DATA, dataMovie)
                startActivity(intent)
            }

//            val factory = ViewModelFactory.getInstance(requireActivity())
//            moviePageViewModel = ViewModelProvider(this, factory)[MoviePageViewModel::class.java]
            moviePageViewModel.movie.observe(viewLifecycleOwner, { movie ->
                if (movie != null){
                    when(movie){
                        is Resource.Loading -> progress_bar.visibility =View.VISIBLE
                        is Resource.Success ->{
                            progress_bar.visibility = View.GONE
                            moviePageListAdapter.setData(movie.data)
                        }
                        is Resource.Error ->{
                            progress_bar.visibility = View.GONE
                            view_error.rootView.visibility =View.VISIBLE
                            view_error.tv_error.text = movie.message ?:getString(R.string.something_wrong)
                        }
                    }
                }
            })

            with(rv_tourism){
                layoutManager =LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter =moviePageListAdapter
            }
        }
    }
}