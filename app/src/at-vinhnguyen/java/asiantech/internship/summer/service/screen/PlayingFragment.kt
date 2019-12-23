package asiantech.internship.summer.service.screen

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import asiantech.internship.summer.service.model.Song
import asiantech.internship.summer.service.service.PlayingService
import asiantech.internship.summer.service.utils.SongUtils
import asiantech.internship.summer.service.utils.SongUtils.DEFAULT_SONG_POSITION
import asiantech.internship.summer.service.utils.SongUtils.EXTRA_SONGS
import kotlinx.android.synthetic.`at-vinhnguyen`.fragment_playing.*

class PlayingFragment : Fragment(), View.OnClickListener {

    private lateinit var onPlaySongListener: PlaySongListener
    private lateinit var playingService: PlayingService
    private var handler = Handler()

    companion object {
        private const val CURRENT_SONG_ID = "current_song_id"
        private var currentSongPosition = 0
        private lateinit var songs: ArrayList<Song>
        fun newInstance(songs: ArrayList<Song>, currentSongId: Int): PlayingFragment {
            val bundle = Bundle()
            bundle.putInt(CURRENT_SONG_ID, currentSongId)
            bundle.putParcelableArrayList(EXTRA_SONGS, songs)
            val playingFragment = PlayingFragment()
            playingFragment.arguments = bundle
            return playingFragment
        }
    }

    private val serviceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val songBinder: PlayingService.SongBinder = service as PlayingService.SongBinder
            playingService = songBinder.getService()
            seekBarPlaying.max = playingService.getDuration()
            seekBarPlaying.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                    if (p1?.action == MotionEvent.ACTION_MOVE) {
                        if (p0?.id == R.id.seekBarPlaying) {
                            playingService.seekTo(seekBarPlaying.progress)
                        }
                    }
                    return true
                }
            })
            updateUI()
        }

        override fun onServiceDisconnected(name: ComponentName) {
        }
    }

    private fun setOnPlaySongListener(playSongListener: PlaySongListener) {
        onPlaySongListener = playSongListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_playing, container, false)
        getData()
        activity?.startService(context?.let { PlayingService.getPlayingServiceIntent(it, songs, currentSongPosition) })
        return view
    }

    private fun getData() {
        arguments?.apply {
            currentSongPosition = getInt(CURRENT_SONG_ID, DEFAULT_SONG_POSITION)
            songs = getParcelableArrayList<Song>(EXTRA_SONGS) as ArrayList<Song>
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.apply {
            initView(this)
            val intent = Intent(this, PlayingService::class.java)
            activity?.bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE)
        }
    }

    private fun initView(context: Context) {
        tvSongName.text = songs[currentSongPosition].title
        tvArtist.text = songs[currentSongPosition].artist
        tvDuration.text = SongUtils.convertToStringTime(songs[currentSongPosition].duration)
        imgSongArt.setImageBitmap(SongUtils.getSongArt(songs[currentSongPosition].songArt, context))
        imgPlay.setOnClickListener(this)
        imgShuffle.setOnClickListener(this)
        imgPrevious.setOnClickListener(this)
        imgNext.setOnClickListener(this)
        imgRepeat.setOnClickListener(this)
        val playingService = PlayingService.getPlayingServiceIntent(context, songs, currentSongPosition)
        context.startService(playingService)
    }

    fun updateUI() {
        activity?.runOnUiThread(object : Runnable {
            override fun run() {
                val progress = playingService.getCurrentPosition()
                seekBarPlaying.progress = progress
                tvCurrentTime.text = SongUtils.convertToStringTime(playingService.getCurrentPosition())
                handler.postDelayed(this, 333)
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as SoundCloudActivity).apply { setOnPlaySongListener(this) }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.imgPlay -> {
                imgPlay.setImageResource(R.drawable.ic_pause_black_80dp)
                onPlaySongListener.onPlay()
            }
            R.id.imgNext -> {
                onPlaySongListener.onNext()
            }
            R.id.imgPrevious -> {
                onPlaySongListener.onPrevious()
            }
            R.id.imgShuffle -> onPlaySongListener.changeShuffle()
            R.id.imgRepeat -> onPlaySongListener.changeLoop()
        }
    }
}
