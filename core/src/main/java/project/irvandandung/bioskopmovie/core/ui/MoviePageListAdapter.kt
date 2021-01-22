package project.irvandandung.bioskopmovie.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_list_movie.view.*
import project.irvandandung.bioskopmovie.core.BuildConfig
import project.irvandandung.bioskopmovie.core.R
import project.irvandandung.bioskopmovie.core.domain.model.Movie

class MoviePageListAdapter : RecyclerView.Adapter<MoviePageListAdapter.ListViewHolder>() {

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?){
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie, parent, false))

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data =listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(data : Movie){
            with(itemView){
                Glide.with(context)
                    .load(BuildConfig.URL_POSTER+data.backdrop_path)
                    .into(iv_item_image)
                tv_item_title.text = data.title
                tv_item_realese_date.text = data.release_date
            }
        }

        init {
            itemView.rootView.setOnClickListener{
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}