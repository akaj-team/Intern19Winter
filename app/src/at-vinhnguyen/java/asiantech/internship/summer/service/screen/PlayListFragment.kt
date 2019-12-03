package asiantech.internship.summer.service.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R
import asiantech.internship.summer.service.adapter.SongAdapter
import asiantech.internship.summer.service.model.Song
import kotlinx.android.synthetic.`at-vinhnguyen`.fragment_playlist.*

class PlayListFragment : Fragment() {

    companion object {
        private const val SONG_LIST = "song_list"
        fun newInstance(songList: ArrayList<Song>): PlayListFragment {
            val bundle = Bundle()
            bundle.putParcelableArrayList(SONG_LIST, songList)
            return PlayListFragment().apply { arguments = bundle }
        }
    }

    var songList = ArrayList<Song>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_playlist, container, false)
        getData()
        return view
    }

    private fun getData() {
        arguments?.getParcelableArrayList<Song>(SONG_LIST)?.apply { songList = this }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewPlaylist.setHasFixedSize(true)
        recyclerViewPlaylist.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        val adapterSong = SongAdapter(songList, context!!)
        recyclerViewPlaylist.adapter = adapterSong
    }
}
