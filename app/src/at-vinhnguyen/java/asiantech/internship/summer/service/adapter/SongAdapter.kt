package asiantech.internship.summer.service.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R
import asiantech.internship.summer.service.model.Song
import asiantech.internship.summer.service.screen.SoundCloudActivity
import asiantech.internship.summer.service.utils.OnRecyclerViewItemClick
import asiantech.internship.summer.service.utils.SongUtils
import java.io.File


class SongAdapter(val context: Context, val songsList: MutableList<Song>) : RecyclerView.Adapter<SongAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_song, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return songsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(position)
        val soundCloudActivity: SoundCloudActivity = context as SoundCloudActivity
        holder.setRecyclerViewItemClickListener(soundCloudActivity)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        lateinit var onRecyclerViewItemClickListener: OnRecyclerViewItemClick
        val tvSongName = itemView.findViewById<TextView>(R.id.tvSongName)
        val tvArtist = itemView.findViewById<TextView>(R.id.tvArtist)
        val imgAlbumArt = itemView.findViewById<ImageView>(R.id.imgViewAlbumArt)

        fun bindView(position: Int) {
            itemView.setOnClickListener(this)
            tvSongName.text = songsList[position].title
            tvArtist.text = songsList[position].artist
            imgAlbumArt.setImageBitmap(SongUtils.getSongArt(File(songsList[position].songArt).absolutePath, context))
        }

        fun setRecyclerViewItemClickListener(onRecyclerViewItemClick: OnRecyclerViewItemClick) {
            onRecyclerViewItemClickListener = onRecyclerViewItemClick
        }

        override fun onClick(p0: View?) {
            onRecyclerViewItemClickListener.onRecyclerViewItemClickListener(adapterPosition)
        }
    }
}
