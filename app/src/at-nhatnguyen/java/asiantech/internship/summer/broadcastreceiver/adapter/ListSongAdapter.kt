package asiantech.internship.summer.broadcastreceiver.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R
import asiantech.internship.summer.broadcastreceiver.model.SongModel
import asiantech.internship.summer.broadcastreceiver.model.Utils

class ListSongAdapter(private val listSong: MutableList<SongModel>, var mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onClick: OnClick? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_song_item, parent, false)
        return ListSongModelViewHolder(view)
    }

    override fun getItemCount() = listSong.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ListSongModelViewHolder) {
            holder.onBindData(position)
        }
    }

    inner class ListSongModelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvSongName: TextView = itemView.findViewById(R.id.tvName)
        private val tvArtist: TextView = itemView.findViewById(R.id.tvArtist)
        private val tvDuration: TextView = itemView.findViewById(R.id.tvDuration)
        private val imgSong: ImageView = itemView.findViewById(R.id.imgPlay)
        fun onBindData(position: Int) {
            val songModel = listSong[position]
            tvSongName.text = songModel.songName
            tvArtist.text = songModel.artist
            tvDuration.text = getDuration(songModel.duration)
            val bitmap = Utils.songImg(mContext, songModel.path)
            if (bitmap != null) {
                imgSong.setImageBitmap(bitmap)
            } else imgSong.setImageResource(R.drawable.ic_song)


            itemView.setOnClickListener {
                onClick?.click(songModel)
            }
        }
    }

    fun getDuration(duration: Int): String {
        val seconds = duration / 1000 % 60
        val minutes = ((duration - seconds) / 1000 / 60).toLong()
        return String.format("%02d: %02d", minutes, seconds)
    }

    fun click(itemOnclick: OnClick) {
        this.onClick = itemOnclick
    }

    interface OnClick {
        fun click(songModel: SongModel)
    }
}
