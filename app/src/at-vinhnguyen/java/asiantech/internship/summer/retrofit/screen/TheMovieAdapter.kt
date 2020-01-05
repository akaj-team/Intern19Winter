package asiantech.internship.summer.retrofit.screen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R
import asiantech.internship.summer.retrofit.data.model.Movie
import asiantech.internship.summer.retrofit.data.model.ResultsItem
import asiantech.internship.summer.retrofit.utils.Constant.BASE_IMG_URL
import com.bumptech.glide.Glide

class TheMovieAdapter(var movies: List<ResultsItem>, var context: Context) : RecyclerView.Adapter<TheMovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie_home, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPoster: ImageView = itemView.findViewById(R.id.imgPoster)
        var txtMovieName: TextView = itemView.findViewById(R.id.txtMovieName)
        var txtDate: TextView = itemView.findViewById(R.id.txtDate)
        fun bindData(position: Int) {
            Glide.with(context)
                    .load(BASE_IMG_URL + movies[position].posterPath)
                    .placeholder(R.drawable.profile_image)
                    .into(imgPoster)
            txtMovieName.text = movies[position].title
            txtDate.text = movies[position].releaseDate
        }
    }
}