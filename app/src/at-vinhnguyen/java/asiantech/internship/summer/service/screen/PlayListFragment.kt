package asiantech.internship.summer.service.screen

import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
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
        val MUSIC_URI: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        fun newInstance(): PlayListFragment {
            return PlayListFragment()
        }
    }

    var songList = ArrayList<Song>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_playlist, container, false)
        getData()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        recyclerViewPlaylist.setHasFixedSize(true)
        recyclerViewPlaylist.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        val adapterSong: SongAdapter
        context?.apply {
            adapterSong = SongAdapter(songList, this)
            recyclerViewPlaylist.adapter = adapterSong
        }
    }

    private fun getData() {
        getSongsList()
    }

    private fun getSongsList() {
        val cursorCols = arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA
        )

        val cursor = context?.contentResolver?.query(MUSIC_URI, cursorCols, null, null, MediaStore.Audio.Media.TITLE)
        cursor?.let {
            it.moveToFirst()
            val idIndex = it.getColumnIndex(cursorCols[0])
            val titleIndex = it.getColumnIndex(cursorCols[1])
            val artistIndex = it.getColumnIndex(cursorCols[2])
            val durationIndex = it.getColumnIndex(cursorCols[3])
            val songArtIndex = it.getColumnIndex(cursorCols[4])
            while (!it.isAfterLast) {
                songList.add(Song(it.getInt(idIndex), it.getString(titleIndex), it.getString(artistIndex), it.getInt(durationIndex), it.getString(songArtIndex)))
                it.moveToNext()
            }
        }
        cursor?.close()
    }
}
