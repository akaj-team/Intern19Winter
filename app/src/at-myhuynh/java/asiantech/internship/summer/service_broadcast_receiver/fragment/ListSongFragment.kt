package asiantech.internship.summer.service_broadcast_receiver.fragment

import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import asiantech.internship.summer.R
import asiantech.internship.summer.service_broadcast_receiver.Song
import asiantech.internship.summer.service_broadcast_receiver.adapter.ListSongAdapter
import kotlinx.android.synthetic.`at-myhuynh`.fragment_list_song.*

class ListSongFragment : Fragment() {

    private lateinit var listSong: MutableList<Song>
    private lateinit var listSongAdapter: ListSongAdapter

    companion object {
        fun newInstance() = ListSongFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_song, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getListSong()
        Log.d("xxx", "${listSong.size}")
        rvListSong.layoutManager = LinearLayoutManager(requireContext())
        listSongAdapter = ListSongAdapter(listSong)
        rvListSong.adapter = listSongAdapter
    }

    private fun getListSong() {
        val contentResolver = requireContext().contentResolver
        val musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ARTIST)
        val cursor = contentResolver.query(musicUri, projection, null, null, null)

        listSong = mutableListOf()
        if (cursor != null && cursor.moveToFirst()) {
            do {
                val songName = cursor.getString(0)
                val songArt = cursor.getString(1)
                Log.d("xxx", songName)
                listSong.add(Song(songName, songArt))
            } while (cursor.moveToNext())

            cursor.close()
        }
    }
}
