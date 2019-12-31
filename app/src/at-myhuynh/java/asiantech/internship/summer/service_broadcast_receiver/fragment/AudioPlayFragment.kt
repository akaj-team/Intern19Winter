package asiantech.internship.summer.service_broadcast_receiver.fragment

import android.animation.ObjectAnimator
import android.content.*
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import asiantech.internship.summer.service_broadcast_receiver.Song
import asiantech.internship.summer.service_broadcast_receiver.Utils
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.ACTION_BACK_NOTIFY
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.ACTION_CLEAR_NOTIFY
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.ACTION_NEXT_AUTO
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.ACTION_NEXT_NOTIFY
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.ACTION_PAUSE_NOTIFY
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.ARG_Is_PLAYING
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.ARG_LIST_SONG
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.ARG_POSITION_SONG
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.ARG_SONG
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.SONG_ART
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.SONG_NAME
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.SONG_URI
import asiantech.internship.summer.service_broadcast_receiver.service.AudioService
import asiantech.internship.summer.service_broadcast_receiver.service.AudioServiceBinder
import kotlinx.android.synthetic.`at-myhuynh`.fragment_audio_play.*

class AudioPlayFragment : Fragment(), View.OnClickListener {
    private var songs: MutableList<Song>? = null
    private var position = 0
    private var isPlaying = true
    private var isShuffle = false
    private var repeat = 0
    private var audioServiceBinder: AudioServiceBinder? = null
    private var audioSeekBarUpdateHandler: Handler? = null
    private var imageSongAnimator: ObjectAnimator? = null

    companion object {
        fun newInstance(song: Song, isPlaying: Boolean, songs: MutableList<Song>, position: Int) = AudioPlayFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_SONG, song)
                putParcelableArrayList(ARG_LIST_SONG, ArrayList(songs))
                putBoolean(ARG_Is_PLAYING, isPlaying)
                putInt(ARG_POSITION_SONG, position)
            }
        }

        private class SeekBarHandler(private val audioPlayFragment: AudioPlayFragment) : Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                if (msg.what == AudioServiceBinder.UPDATE_AUDIO_PROGRESS_BAR) {
                    if (audioPlayFragment.audioServiceBinder != null) {
                        val currProgress = audioPlayFragment.audioServiceBinder?.audioProgress()
                        val currPosition = audioPlayFragment.audioServiceBinder?.currentAudioPosition()?.toLong()
                        if (currProgress != null && audioPlayFragment.seekBar != null) {
                            audioPlayFragment.seekBar.progress = currProgress
                        }

                        if (currPosition != null && audioPlayFragment.tvTimePlaying != null) {
                            audioPlayFragment.tvTimePlaying.text = Utils.convertLongToTime(currPosition)
                        }
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            isPlaying = it.getBoolean(ARG_Is_PLAYING)
            songs = it.getParcelableArrayList(ARG_LIST_SONG)
            position = it.getInt(ARG_POSITION_SONG)
        }
        isShuffle = Utils.readIsShuffle(requireContext())
        repeat = Utils.readAudioRepeat(requireContext())
        Log.d("xxx", "repeat: $repeat")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_audio_play, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        imgPlay.setOnClickListener(this)
        imgNext.setOnClickListener(this)
        imgBack.setOnClickListener(this)
        imgShuffle.setOnClickListener(this)
        imgRepeat.setOnClickListener(this)
        setRotateImageSong()

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                seekBar?.let {
                    changeSeekBar(it.progress)
                }
            }
        })
        bindAudioService()
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(ACTION_NEXT_NOTIFY)
        filter.addAction(ACTION_BACK_NOTIFY)
        filter.addAction(ACTION_PAUSE_NOTIFY)
        filter.addAction(ACTION_CLEAR_NOTIFY)
        filter.addAction(ACTION_NEXT_AUTO)
        requireContext().registerReceiver(notificationReceiver, filter)
        bindUpdateSeekBar()
    }

    override fun onPause() {
        super.onPause()
        requireContext().unregisterReceiver(notificationReceiver)
    }

    override fun onClick(v: View?) {
        when (v) {
            imgPlay -> {
                if (!isPlaying) {
                    imageSongAnimator?.resume()
                    playSong()
                    bindUpdateSeekBar()
                } else {
                    audioServiceBinder?.pauseAudio()
                    imageSongAnimator?.pause()
                }
                isPlaying = !isPlaying
                changeIconAudioPlay()
            }

            imgNext -> {
                audioServiceBinder?.nextAudio()
                initNextAudio()
            }

            imgBack -> {
                audioServiceBinder?.previousAudio()
                initPreviousAudio()
            }

            imgShuffle -> {
                isShuffle = !isShuffle
                if (isShuffle) {
                    imgShuffle.setImageResource(R.drawable.ic_shuffle_red_24dp)
                } else {
                    imgShuffle.setImageResource(R.drawable.ic_shuffle_black_24dp)
                }
                Utils.writeSharedPreferences(requireContext(), isShuffle, repeat)
            }

            imgRepeat -> {
                if (repeat < 2) repeat++
                else if (repeat == 2) repeat = 0
                when (repeat) {
                    0 -> imgRepeat.setImageResource(R.drawable.ic_repeat_white_24dp)
                    1 -> imgRepeat.setImageResource(R.drawable.ic_repeat_one_red_24dp)
                    2 -> imgRepeat.setImageResource(R.drawable.ic_repeat_red_24dp)
                }
                Log.d("xxx", "repeat: $repeat")
                Utils.writeSharedPreferences(requireContext(), isShuffle, repeat)
            }
        }
    }

    private fun initNextAudio() {
        position = Utils.readAudioPosition(requireContext())
        Log.d("xxx", "initNextAudio: $position")
        songs?.let {
            initView()
            requireContext().stopService(Intent(requireContext(), AudioService::class.java))
            startService(songs)
            bindUpdateSeekBar()
        }
    }

    private fun initPreviousAudio() {
        position = Utils.readAudioPosition(requireContext())
        songs?.let {
            initView()
            requireContext().stopService(Intent(requireContext(), AudioService::class.java))
            startService(songs)
            bindUpdateSeekBar()
        }
    }

    private val notificationReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d("xxx", "Fragment Play")
            when (intent?.action) {
                ACTION_NEXT_NOTIFY -> {
                    initNextAudio()

                }

                ACTION_BACK_NOTIFY -> {
                    initPreviousAudio()
                }

                ACTION_PAUSE_NOTIFY -> {
                    isPlaying = !isPlaying
                    changeIconAudioPlay()
                }

                ACTION_NEXT_AUTO -> initNextAudio()
            }
        }
    }

    private fun startService(songs: MutableList<Song>?) {
        val song = songs?.get(position)
        val intent = Intent(requireContext(), AudioService::class.java)
        intent.putExtra(SONG_NAME, song?.name)
        intent.putExtra(SONG_ART, song?.artist)
        intent.putExtra(SONG_URI, song?.path.toString())
        requireContext().startService(intent)
    }

    private fun bindUpdateSeekBar() {
        createAudioSeekBarUpdater()
        audioServiceBinder?.audioSeekBarUpdateHandler = audioSeekBarUpdateHandler
    }

    private fun changeSeekBar(currentPosition: Int) {
        audioServiceBinder?.updateCurrentPosition(currentPosition)
    }

    private fun playSong() {
        audioServiceBinder?.audioFileUri = Uri.parse(songs?.get(position)?.path)
        audioServiceBinder?.context = requireContext()
        audioServiceBinder?.startAudio()
    }

    override fun onDestroy() {
        super.onDestroy()
        unBoundAudioService()
    }

    private fun setRotateImageSong() {
        imageSongAnimator = ObjectAnimator.ofFloat(imgSong, "rotation", 0f, 360f)
        imageSongAnimator?.duration = 200000
        imageSongAnimator?.repeatCount = ObjectAnimator.INFINITE
        imageSongAnimator?.repeatMode = ObjectAnimator.RESTART
        imageSongAnimator?.interpolator = LinearInterpolator()
        imageSongAnimator?.start()
    }

    private fun initView() {
        isPlaying = true
        changeIconAudioPlay()
        val song = songs?.get(position)
        song?.let {
            tvSongName.text = it.name
            tvSongArtist.text = it.artist
            tvDuration.text = Utils.convertLongToTime(it.duration)
        }
        tvTimePlaying.text = getString(R.string.edittext_time_start)
        val imageBitmap = Utils.getCoverPicture(requireContext(), Uri.parse(song?.path))
        if (imageBitmap != null) {
            imgSong.setImageBitmap(imageBitmap)
        } else {
            imgSong.setImageResource(R.drawable.ic_play_circle_outline_black_200dp)
        }

        if (isShuffle) {
            imgShuffle.setImageResource(R.drawable.ic_shuffle_red_24dp)
        } else {
            imgShuffle.setImageResource(R.drawable.ic_shuffle_black_24dp)
        }

        when (repeat) {
            0 -> imgRepeat.setImageResource(R.drawable.ic_repeat_white_24dp)
            1 -> imgRepeat.setImageResource(R.drawable.ic_repeat_one_red_24dp)
            2 -> imgRepeat.setImageResource(R.drawable.ic_repeat_red_24dp)
        }
    }

    private fun changeIconAudioPlay() {
        if (isPlaying) {
            imgPlay.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp)
            imageSongAnimator?.resume()

        } else {
            imgPlay.setImageResource(R.drawable.ic_play_circle_outline_black_24dp)
            imageSongAnimator?.pause()
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

    private fun bindAudioService() {
        val intent = Intent(requireContext(), AudioService::class.java)
        requireContext().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
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
}
