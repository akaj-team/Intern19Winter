package asiantech.internship.summer.service_broadcast_receiver.fragment

import android.content.*
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import asiantech.internship.summer.R
import asiantech.internship.summer.service_broadcast_receiver.Song
import asiantech.internship.summer.service_broadcast_receiver.Utils
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
    private var isPlay = false
    private var audioServiceBinder: AudioServiceBinder? = null
    private var audioSeekBarUpdateHandler: Handler? = null

    companion object {
        fun newInstance() = ListSongFragment()
        private class SeekBarHandler(private val listSongFragment: ListSongFragment) : Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                if (msg.what == AudioServiceBinder.UPDATE_AUDIO_PROGRESS_BAR) {
                    if (listSongFragment.audioServiceBinder != null) {
                        val currProgress = listSongFragment.audioServiceBinder?.audioProgress()
                        if (currProgress != null) {
                            listSongFragment.seekBar.progress = currProgress
                            Log.d("xxx", currProgress.toString())
                        }
                    }
                }
            }
        }
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
        listSongAdapter = ListSongAdapter(requireContext(), listSong)
        rvListSong.adapter = listSongAdapter
        setOnClickSongItem(listSongAdapter)
        setMusicPlay()
        seekBar.max = 100
        bindAudioService()

        imgBack.setOnClickListener(this)
        imgNext.setOnClickListener(this)
        imgPlay.setOnClickListener(this)
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            audioServiceBinder = null
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            audioServiceBinder = service as? AudioServiceBinder
        }
    }

    private fun bindAudioService() {
        audioServiceBinder?.let {
            val intent = Intent(requireContext(), AudioService().javaClass)
            requireContext().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }

    private fun unBoundAudioService() {
        audioServiceBinder?.let {
            requireContext().unbindService(serviceConnection)
        }
    }

    private fun createAudioSeekBarUpdater() {
        if (audioSeekBarUpdateHandler == null) {
            audioSeekBarUpdateHandler = SeekBarHandler(this)
        }

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
                createAudioSeekBarUpdater()
                audioServiceBinder?.audioSeekBarUpdateHandler = audioSeekBarUpdateHandler
                audioServiceBinder?.stopAudio()
                playSong()
            }

            imgBack -> {
                if (position > 0) {
                    position--
                } else {
                    position = listSong.size - 1
                }
                setMusicPlay()
                audioServiceBinder?.stopAudio()
                playSong()
                createAudioSeekBarUpdater()
                audioServiceBinder?.audioSeekBarUpdateHandler = audioSeekBarUpdateHandler
            }

            imgPlay -> {
                isPlay = if (!isPlay) {
                    imgPlay.setImageResource(R.drawable.ic_play_circle_outline_black_24dp)
                    audioServiceBinder?.pauseAudio()
                    createAudioSeekBarUpdater()
                    audioServiceBinder?.audioSeekBarUpdateHandler = audioSeekBarUpdateHandler
                    true
                } else {
                    imgPlay.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp)
                    playSong()
                    createAudioSeekBarUpdater()
                    audioServiceBinder?.audioSeekBarUpdateHandler = audioSeekBarUpdateHandler
                    false
                }
            }
        }
    }

    private fun playSong() {
        audioServiceBinder?.audioFileUri = listSong[position].path
        audioServiceBinder?.context = requireContext()
        audioServiceBinder?.startAudio()
    }

    private fun setMusicPlay() {
        val song = listSong[position]
        tvSongName.text = song.name
        tvSongArtistPlay.text = song.artist
        tvTimePlay.text = song.duration
        val imageBitmap = Utils.getCoverPicture(requireContext(), song.path)
        if (imageBitmap != null) {
            imgSongPlay.setImageBitmap(imageBitmap)
        } else {
            imgSongPlay.setImageResource(R.drawable.ic_play_circle_outline_black_24dp)
        }
    }

    private fun setOnClickSongItem(adapter: ListSongAdapter) {
        adapter.setOnClickSongItem(object : HandleOnclickSongItem {
            override fun songItemOnClick(song: Song) {
                position = listSong.lastIndexOf(song)
                setMusicPlay()
                audioServiceBinder?.stopAudio()
                playSong()
            }
        })
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

                Log.d("xxx", songUri.toString())
                listSong.add(Song(songName, songArt, songUri, duration))
            } while (cursor.moveToNext())
            cursor.close()
        }
    }
}
