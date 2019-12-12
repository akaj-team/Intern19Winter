package asiantech.internship.summer.music

import android.annotation.SuppressLint
import android.content.ContentUris
import android.media.MediaPlayer
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-tamle`.fragment_list_song.*

class ListSongFragment : Fragment() {
    private var listSong = mutableListOf<SongModel>()

    companion object {
        fun newInstance() = ListSongFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list_song, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = SongAdapter(listSong, requireContext())
        listmusic.layoutManager = LinearLayoutManager(activity) as RecyclerView.LayoutManager?
        listmusic.adapter = adapter
    }
}