package asiantech.internship.summer.music

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import asiantech.internship.summer.MainActivity
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-tamle`.fragment_list_song.*

class ListSongFragment : Fragment() {
    private var listSong = mutableListOf<SongModel>()
    var mediaPlayer: MediaPlayer? = null
    private val MY_PERMISSION_REQUEST = 1

    companion object {
        fun newInstance() = ListSongFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list_song, container, false)
        //var adapterSong = SongAdapter()
        return view
        initview()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listSong.addAll(getMusic())
        val adapter = SongAdapter(listSong, requireContext())
        listmusic.layoutManager = LinearLayoutManager(activity)
        listmusic.adapter = adapter

        // getMusic()
    }


    //Get lisst nhac
    @SuppressLint("Recycle")
    private fun getMusic(): List<SongModel> {
        listSong = mutableListOf()
        val contentResolver = context?.contentResolver
        val songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val songCursor = contentResolver?.query(songUri, null, null, null, null)
        if (songCursor != null && songCursor.moveToFirst()) {
            val songTitle = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE)
            do {
                val currentTitle = songCursor.getString(songTitle)
                listSong.add(SongModel(currentTitle))
            } while (songCursor.moveToNext())
        }
        return listSong
    }

    private fun initview() {
        if (context?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.READ_EXTERNAL_STORAGE) } != PackageManager.PERMISSION_GRANTED) {

            //Ask for permission
            ActivityCompat.requestPermissions(context as Activity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 0)
        } else {
            getMusic()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getMusic()
        } else {
        }
    }
}