package asiantech.internship.summer.service.screen

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import asiantech.internship.summer.service.model.Song
import asiantech.internship.summer.service.service.OnMediaPlayChange
import asiantech.internship.summer.service.service.PlayingService
import asiantech.internship.summer.service.utils.SongUtils
import asiantech.internship.summer.service.utils.SongUtils.DEFAULT_SONG_POSITION
import asiantech.internship.summer.service.utils.SongUtils.EXTRA_SONGS
import kotlinx.android.synthetic.`at-vinhnguyen`.fragment_playing.*

class PlayingFragment : Fragment(), View.OnClickListener, OnMediaPlayChange {

    private lateinit var onPlaySongListener: PlaySongListener
    private lateinit var playingService : PlayingService
    companion object {
        private const val CURRENT_SONG_ID = "current_song_id"
        private var currentSongId = 0
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
            playingService.setOnMediaChangeListener(this@PlayingFragment)
        }

        override fun onServiceDisconnected(name: ComponentName) {
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_playing, container, false)
        getData()
        return view
    }

    private fun getData() {
        arguments?.apply {
            currentSongId = getInt(CURRENT_SONG_ID, DEFAULT_SONG_POSITION)
            songs = getParcelableArrayList<Song>(EXTRA_SONGS) as ArrayList<Song>
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.apply {
            initView(this)
        }
    }

    private fun initView(context: Context) {
        tvSongName.text = songs[currentSongId].title
        tvArtist.text = songs[currentSongId].artist
        tvDuration.text = SongUtils.convertToStringTime(songs[currentSongId].duration)
        imgSongArt.setImageBitmap(SongUtils.getSongArt(songs[currentSongId].songArt, context))
        imgPlay.setOnClickListener(this)
        imgShuffle.setOnClickListener(this)
        imgPrevious.setOnClickListener(this)
        imgNext.setOnClickListener(this)
        imgRepeat.setOnClickListener(this)
        val playingService = PlayingService.getPlayingServiceIntent(context, songs, currentSongId)
        context.startService(playingService)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.imgPlay -> onPlaySongListener.onPlay()
            R.id.imgNext -> onPlaySongListener.onNext()
            R.id.imgPrevious -> onPlaySongListener.onPrevious()
            R.id.imgShuffle -> onPlaySongListener.changeShuffle()
            R.id.imgRepeat -> onPlaySongListener.changeLoop()
        }
    }
}
