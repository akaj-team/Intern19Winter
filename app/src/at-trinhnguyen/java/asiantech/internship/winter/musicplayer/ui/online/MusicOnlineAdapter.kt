package asiantech.internship.winter.musicplayer.ui.online

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R
import asiantech.internship.winter.musicplayer.network.mockapi.SongMockApiResponse
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class MusicOnlineAdapter(private val context: Context) : RecyclerView.Adapter<MusicOnlineAdapter.SongViewHolder>() {
    private var onSongClicked: SongClicked? = null
    internal var onMoreClick: (position: Int) -> Unit = {}
    private var songs = mutableListOf<SongMockApiResponse>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): SongViewHolder {
        return SongViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_list_song, viewGroup, false))
    }

    override fun onBindViewHolder(viewHolder: SongViewHolder, position: Int) {
        viewHolder.onBind(position)
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    fun submitSongs(songs: List<SongMockApiResponse>) {
        this.songs.clear()
        this.songs.addAll(songs)
        notifyDataSetChanged()
    }

    fun setOnSongClicked(songClick: SongClicked) {
        this.onSongClicked = songClick
    }

    interface SongClicked {
        fun onSongClicked(song: SongMockApiResponse)
    }

    inner class SongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemContainer: ConstraintLayout = itemView.findViewById(R.id.container)
        private val title: TextView = itemView.findViewById(R.id.tvSongTitle)
        private val artist: TextView = itemView.findViewById(R.id.tvArtist)
        private val imgSongArt = itemView.findViewById<ImageView>(R.id.imgSongArt)
        private val btnMore = itemView.findViewById<ImageButton>(R.id.imgBtnMore)

        init {
            itemContainer.setOnClickListener {
                onSongClicked?.onSongClicked(songs[layoutPosition])
            }

            btnMore.setOnClickListener {
                onMoreClick.invoke(layoutPosition)
            }
        }

        fun onBind(position: Int) {
            val song = songs[position]
            title.text = song.title
            artist.text = song.artist
            Glide.with(context)
                    .load(song.image)
                    .transform(RoundedCorners(4))
                    .priority(Priority.LOW)
                    .error(R.drawable.ic_song)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.ic_song)
                    .into(imgSongArt)
        }
    }
}
