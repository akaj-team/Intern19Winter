package asiantech.internship.summer.service_broadcast_receiver.fragment

import android.content.ComponentName
import android.content.ContentUris
import android.content.Intent
import android.content.ServiceConnection
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import asiantech.internship.summer.R
import asiantech.internship.summer.service_broadcast_receiver.MusicActivity
import asiantech.internship.summer.service_broadcast_receiver.Song
import asiantech.internship.summer.service_broadcast_receiver.Utils
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.ACTION_PAUSE
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.ACTION_PLAY
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.SONG_ART
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.SONG_ART_NAME
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.SONG_NAME
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.SONG_URI
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.STATE_NEED_PLAY
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.STATE_PLAYING
import asiantech.internship.summer.service_broadcast_receiver.`interface`.HandleOnclickSongItem
import asiantech.internship.summer.service_broadcast_receiver.adapter.ListSongAdapter
import asiantech.internship.summer.service_broadcast_receiver.service.AudioService
import asiantech.internship.summer.service_broadcast_receiver.service.AudioServiceBinder
import kotlinx.android.synthetic.`at-myhuynh`.fragment_list_song.*
import java.util.concurrent.TimeUnit


class ListSongFragment : Fragment(), View.OnClickListener {

    private lateinit var listSong: MutableList<Song>
    private lateinit var listSongAdapter: ListSongAdapter
    private var position = 0
    private var isPlaying = false
    private var audioServiceBinder: AudioServiceBinder? = null

    companion object {
        private const val STATE_IS_PLAYING = "isPlaying"
        fun newInstance() = ListSongFragment()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(STATE_IS_PLAYING, isPlaying)
        super.onSaveInstanceState(outState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list_song, container, false)
        savedInstanceState?.let {
            isPlaying = it.getBoolean(STATE_IS_PLAYING)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListSong()
        initView()
        initOnClickListener()
    }

    private fun initOnClickListener() {
        setOnClickSongItem(listSongAdapter)
        imgBack.setOnClickListener(this)
        imgNext.setOnClickListener(this)
        imgPlay.setOnClickListener(this)
    }

    private fun initView() {
        setMusicPlay()
        changeIconAudioPlay()
    }

    private fun initListSong() {
        getListSong()
        Log.d("xxx", "${listSong.size}")
        rvListSong.layoutManager = LinearLayoutManager(requireContext())
        listSongAdapter = ListSongAdapter(requireContext(), listSong)
        rvListSong.adapter = listSongAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        unBoundAudioService()
    }

    override fun onClick(v: View?) {
        when (v) {
            imgNext -> {
                if (position < listSong.size - 1) {
                    position++
                } else {
                    position = 0
                }
                setMusicPlay()
                startService(listSong[position])
            }

            imgBack -> {
                if (position > 0) {
                    position--
                } else {
                    position = listSong.size - 1
                }
                setMusicPlay()
                startService(listSong[position])
            }

            imgPlay -> {
                if (!(activity as MusicActivity).isMyServiceRunning(AudioService::class.java)) {
                    Log.d("xxx", "service is not running")
                    startService(listSong[position])
                } else {
                    Log.d("xxx", "service is running")
                    isPlaying = if (!isPlaying) {
                        changeIconAudioPlay()
                        pauseOrResumeAudio(ACTION_PAUSE)
                        true
                    } else {
                        changeIconAudioPlay()
                        pauseOrResumeAudio(ACTION_PLAY)
                        false
                    }
                }
            }
        }
    }

    private fun setOnClickSongItem(adapter: ListSongAdapter) {
        adapter.setOnClickSongItem(object : HandleOnclickSongItem {
            override fun songItemOnClick(song: Song) {
                if (!isPlaying || (isPlaying && position != listSong.lastIndexOf(song))) {
                    position = listSong.lastIndexOf(song)
                    setMusicPlay()
                    isPlaying = true
                    changeIconAudioPlay()
                    Log.d("xxx" , "Can phat")
                    (activity as? MusicActivity)?.replaceFragment(AudioPlayFragment.newInstance(song, isPlaying, STATE_NEED_PLAY), true)
                } else {
                    Log.d("xxx" , "Dang phat")
                    (activity as? MusicActivity)?.replaceFragment(AudioPlayFragment.newInstance(song, isPlaying, STATE_PLAYING), true)
                }
            }

            override fun songItemOnLongClick(song: Song) {
                stopService()
            }
        })
    }

    private fun pauseOrResumeAudio(action: String) {
        val song = listSong[position]
        val intent = Intent(action, Uri.parse(song.path), requireContext(), AudioService::class.java)
        intent.putExtra(SONG_NAME, song.name)
        intent.putExtra(SONG_ART, song.artist)
        intent.putExtra(SONG_URI, song.path)
        requireContext().startService(intent)
    }

    private fun changeIconAudioPlay() {
        if (isPlaying) {
            imgPlay.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp)
        } else {
            imgPlay.setImageResource(R.drawable.ic_play_circle_outline_black_24dp)
        }
    }

    private fun startService(song: Song) {
        val intent = Intent(requireContext(), AudioService::class.java)
        requireContext().stopService(intent)
        isPlaying = true
        changeIconAudioPlay()
        intent.putExtra(SONG_NAME, song.name)
        intent.putExtra(SONG_ART, song.artist)
        intent.putExtra(SONG_URI, song.path.toString())
        requireContext().startService(intent)
    }

    private fun stopService() {
        Toast.makeText(requireContext(), "Stop service", Toast.LENGTH_SHORT).show()
        val intent = Intent(requireContext(), AudioService::class.java)
        requireContext().stopService(intent)
        isPlaying = false
    }

    private fun setMusicPlay() {
        val song = listSong[position]
        tvSongName.text = song.name
        tvSongArtistPlay.text = song.artist
        val imageBitmap = Utils.getCoverPicture(requireContext(), Uri.parse(song.path))
        if (imageBitmap != null) {
            imgSongPlay.setImageBitmap(imageBitmap)
        } else {
            imgSongPlay.setImageResource(R.drawable.ic_play_circle_outline_black_24dp)
        }
    }

    private fun getListSong() {
        val contentResolver = requireContext().contentResolver
        val musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val selection = MediaStore.Audio.Media.IS_MUSIC + " != 0"
        val projection = arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION)
        val cursor = contentResolver.query(musicUri, projection, selection, null, null)

        listSong = mutableListOf()
        if (cursor != null && cursor.moveToFirst()) {
            do {
                val songId = cursor.getLong(0)
                val songName = cursor.getString(1)
                val songArt = cursor.getString(2)
                val songDuration = cursor.getString(3).toLong()
                val minutes = TimeUnit.MILLISECONDS.toMinutes(songDuration)
                val seconds = TimeUnit.MILLISECONDS.toSeconds(songDuration - TimeUnit.MINUTES.toMillis(minutes))
                val songUri = ContentUris.withAppendedId(musicUri, songId)
                var duration: String

                duration = if (minutes < 10) {
                    "0$minutes"
                } else {
                    "$minutes"
                }

                duration += if (seconds < 10) {
                    ":0$seconds"
                } else {
                    ":$seconds"
                }

//                Log.d("xxx", songArt)
                if (songArt != SONG_ART_NAME) {
                    listSong.add(Song(songName, songArt, songUri.toString(), duration))
                }
            } while (cursor.moveToNext())
            cursor.close()
        }
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            audioServiceBinder = null
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            audioServiceBinder = service as? AudioServiceBinder
        }
    }

    private fun unBoundAudioService() {
        audioServiceBinder?.let {
            requireContext().unbindService(serviceConnection)
        }
    }
}
