package asiantech.internship.summer.music

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R

class SongAdapter(private val listSong: MutableList<SongModel>, var mContext: Context) : RecyclerView.Adapter<SongAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item_song, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listSong.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindata(position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tvName: TextView = itemView.findViewById(R.id.tvName)
        fun onBindata(position: Int) {
            val songModel: SongModel = listSong[position]
            tvName.setText(songModel.songName)
        }
    }
}