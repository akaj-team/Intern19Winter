package asiantech.internship.summer.broadcastreceiver.fragment

import android.Manifest
import android.app.Activity
import android.content.*
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.IBinder
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import asiantech.internship.summer.R
import asiantech.internship.summer.broadcastreceiver.Service.MusicService
import asiantech.internship.summer.broadcastreceiver.adapter.ListSongAdapter
import asiantech.internship.summer.broadcastreceiver.model.SongModel
import kotlinx.android.synthetic.`at-nhatnguyen`.fragment_list_song.*

class ListSongFragment : Fragment() {

    private val REQUEST_CODE_READ = 100
    private lateinit var listSong: MutableList<SongModel>
    private var playIntent: Intent? = null
    private var musicBound = false
    private lateinit var musicService: MusicService

    companion object {
        fun newInstance() = ListSongFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_song, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        runTimePermission()
        listSongDevice()
        val adapter = ListSongAdapter(listSong, requireContext())
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter

        setOnclick(adapter)
    }

    private fun listSongDevice() {
        listSong = mutableListOf()
        val contentResolver = context?.contentResolver
        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val cursor = contentResolver?.query(uri, null, null, null, null)

        if (cursor != null && cursor.moveToFirst()) {
            val id = cursor.getColumnIndex(MediaStore.Audio.Media._ID)
            val title = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)
            val artist = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)
            val duration = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)
            do {
                val currentId = cursor.getLong(id)
                val path = ContentUris.withAppendedId(uri, currentId)
                val currentTitle = cursor.getString(title)
                val currentArtist = cursor.getString(artist)
                val currentDuration = cursor.getInt(duration)
                listSong.add(SongModel(currentTitle, currentArtist, currentDuration, path.toString(), currentId))
            } while (cursor.moveToNext())
            cursor.close()
        }
    }

    private fun runTimePermission() {
        val permission = context?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.READ_EXTERNAL_STORAGE) }
        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(context as Activity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE_READ)
    }

    private fun setOnclick(adapter: ListSongAdapter) {
        adapter.click(object : ListSongAdapter.OnClick {
            override fun click(songModel: SongModel) {
                fragmentManager?.beginTransaction()?.replace(R.id.frlActivity, PlayMp3Fragment.newInstance(songModel))?.
                        addToBackStack(null)?.commit()
                val intent = Intent(context, MusicService::class.java)
                intent.putExtra("ID", songModel.songId)
            }
        })
    }

    private var musicConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            musicBound = false
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MusicService.MusicBinder
            // Log.d("xxx","${listSong.size}")

            musicService = binder.getService
            musicService.setList(listSong)
            musicBound = true
        }

    }

    override fun onStart() {
        super.onStart()
        if (playIntent == null) {
            playIntent = Intent(context, MusicService::class.java)
            context?.bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE)
            context?.startService(playIntent)
        }
    }
}
