package asiantech.internship.summer.service.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R
import asiantech.internship.summer.service.model.Song

class SongAdapter(val songsList: MutableList<Song>, val context: Context) : RecyclerView.Adapter<SongAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_song, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return songsList.size
    }

    override fun onBindViewHolder(holder: SongAdapter.ViewHolder, position: Int) {
        holder.bindView(position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvSongName = itemView.findViewById<TextView>(R.id.tvSongName)
        val tvArtists = itemView.findViewById<TextView>(R.id.tvArtist)

        fun bindView(position: Int) {
            tvSongName.text = songsList[position].title
            tvArtists.text = songsList[position].artist
        }
    }
}
