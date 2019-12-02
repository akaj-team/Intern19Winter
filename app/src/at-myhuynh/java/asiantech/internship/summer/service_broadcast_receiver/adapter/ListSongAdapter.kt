package asiantech.internship.summer.service_broadcast_receiver.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R
import asiantech.internship.summer.service_broadcast_receiver.Song

class ListSongAdapter(private val listSong: MutableList<Song>) : RecyclerView.Adapter<ListSongAdapter.ListSongViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item_song, parent, false)
        return ListSongViewHolder(view)
    }

    override fun getItemCount() = listSong.size

    override fun onBindViewHolder(holder: ListSongViewHolder, position: Int) {
        holder.onBind(position)
    }

    inner class ListSongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {
            val songName = itemView.findViewById<TextView>(R.id.tvSongName)
            val songArtist = itemView.findViewById<TextView>(R.id.tvSongArtist)
            val songTime = itemView.findViewById<TextView>(R.id.tvSongTime)
            val songItem = listSong[position]

            songName.text = songItem.name
            songArtist.text = songItem.artist
            songTime.text = songItem.time
        }
    }
}
