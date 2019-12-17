package asiantech.internship.summer.service.screen

import android.content.Context
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
import asiantech.internship.summer.service.utils.SongUtils.EXTRA_SONGS
import kotlinx.android.synthetic.`at-vinhnguyen`.fragment_playlist.*

class PlayListFragment : Fragment() {

    private lateinit var songs: ArrayList<Song>

    companion object {
        fun newInstance(songs: ArrayList<Song>): PlayListFragment {
            val bundle = Bundle()
            bundle.putParcelableArrayList(EXTRA_SONGS, songs)
            val playListFragment = PlayListFragment()
            playListFragment.arguments = bundle
            return playListFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_playlist, container, false)
        getData()
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.apply {
            initView(this)
        }
    }

    private fun getData() {
        arguments?.apply {
            songs = getParcelableArrayList<Song>(EXTRA_SONGS) as ArrayList<Song>
        }
    }

    private fun initView(context: Context) {
        songs = SoundCloudActivity.songs
        val songAdapter = SongAdapter(context, songs)
        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerViewPlaylist.layoutManager = layoutManager
        recyclerViewPlaylist.setHasFixedSize(true)
        recyclerViewPlaylist.adapter = songAdapter
    }
}
