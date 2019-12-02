package asiantech.internship.summer.broadcastreceiver.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R
import asiantech.internship.summer.broadcastreceiver.model.SongModel

class ListSongAdapter(private val mSongName:MutableList<SongModel>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_song_item,parent,false)
        return ListSongModelViewHolder(view)
    }

    override fun getItemCount()= mSongName.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ListSongModelViewHolder){
            holder.onBindData(position)
        }
    }

    inner class ListSongModelViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private val tvSongName:TextView = itemView.findViewById(R.id.tvSongName)
        fun onBindData(position: Int){
            val songModel = mSongName[position]
            tvSongName.text = songModel.songName
        }
    }
}
