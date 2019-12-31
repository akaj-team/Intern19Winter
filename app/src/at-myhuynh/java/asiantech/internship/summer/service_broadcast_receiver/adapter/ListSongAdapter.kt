package asiantech.internship.summer.service_broadcast_receiver.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R
import asiantech.internship.summer.service_broadcast_receiver.Song
import asiantech.internship.summer.service_broadcast_receiver.Utils
import asiantech.internship.summer.service_broadcast_receiver.`interface`.HandleOnclickSongItem

class ListSongAdapter(private val context: Context, private val listSong: MutableList<Song>) : RecyclerView.Adapter<ListSongAdapter.ListSongViewHolder>() {

    private lateinit var songItemOnClick: HandleOnclickSongItem

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item_song, parent, false)
        return ListSongViewHolder(view)
    }

    override fun getItemCount() = listSong.size

    override fun onBindViewHolder(holder: ListSongViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun setOnClickSongItem(handleOnclickSongItem: HandleOnclickSongItem) {
        songItemOnClick = handleOnclickSongItem
    }

    inner class ListSongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {
            val songImage = itemView.findViewById<ImageView>(R.id.imgSong)
            val songName = itemView.findViewById<TextView>(R.id.tvSongName)
            val songArtist = itemView.findViewById<TextView>(R.id.tvSongArtist)
            val songItem = listSong[position]

            val imageBitmap = Utils.getCoverPicture(context, Uri.parse(songItem.path))
            if (imageBitmap != null) {
                songImage.setImageBitmap(imageBitmap)
            } else {
                songImage.setImageResource(R.drawable.ic_playlist)
            }

            songName.text = songItem.name
            songArtist.text = songItem.artist

            itemView.setOnClickListener { songItemOnClick.songItemOnClick(songItem) }
            itemView.setOnLongClickListener {
                songItemOnClick.songItemOnLongClick(songItem)
                true
            }
        }
    }
}
