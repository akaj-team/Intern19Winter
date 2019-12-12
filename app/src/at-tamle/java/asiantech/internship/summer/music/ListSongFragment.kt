package asiantech.internship.summer.music

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.MediaStore
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
        listSong = getMusic()
        val adapter = SongAdapter(listSong, requireContext())
        listmusic.layoutManager = LinearLayoutManager(activity) as RecyclerView.LayoutManager?
        listmusic.adapter = adapter

    }

    @SuppressLint("InlinedApi")
    private fun getMusic(): MutableList<SongModel> {
        val listSong = mutableListOf<SongModel>()
        val contentResolver = context?.contentResolver
        val songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val songCursor = contentResolver?.query(songUri, arrayOf(
//                MediaStore.Audio.Media.ALBUM,
//                MediaStore.Audio.Media.ARTIST,
//                MediaStore.Audio.Media.TRACK,
//                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DATA
//                MediaStore.Audio.Media.DURATION,
//                MediaStore.Audio.Media.YEAR
        ), null, null, null)
        if (songCursor != null && songCursor.moveToFirst()) {
            do {
//                val currentAlbum = songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM))
//                val currentArtist = songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
//                val currentTrack = songCursor.getInt(songCursor.getColumnIndex(MediaStore.Audio.Media.TRACK))
//                val currentTitle = songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
                val currentName = songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME))
                val currentPath = songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.DATA))
//                val currentDuration = songCursor.getInt(songCursor.getColumnIndex(MediaStore.Audio.Media.DURATION))
//                val currentYear = songCursor.getInt(songCursor.getColumnIndex(MediaStore.Audio.Media.YEAR))
                // listSong.add(SongModel(currentAlbum, currentArtist, currentTrack, currentTitle, currentName, currentPath, currentDuration, currentYear))
                listSong.add(SongModel(currentName, currentPath))

            } while (songCursor.moveToNext())
        }
        return listSong
    }

}