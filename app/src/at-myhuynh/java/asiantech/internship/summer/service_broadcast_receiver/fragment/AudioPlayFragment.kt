package asiantech.internship.summer.service_broadcast_receiver.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import asiantech.internship.summer.service_broadcast_receiver.Song
import asiantech.internship.summer.service_broadcast_receiver.Utils
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.ACTION_PAUSE
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.ACTION_PLAY
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.ACTION_START
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.ARG_Is_PLAYING
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.ARG_SONG
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.ARG_STATE_SONG
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.SONG_ART
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.SONG_NAME
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.SONG_URI
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.STATE_NEED_PLAY
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.STATE_PLAYING
import asiantech.internship.summer.service_broadcast_receiver.service.AudioService
import kotlinx.android.synthetic.`at-myhuynh`.fragment_audio_play.*

class AudioPlayFragment : Fragment(), View.OnClickListener {
    private var song: Song? = null
    private var isPlaying = false
    private var stateSong: String? = null

    companion object {
        fun newInstance(song: Song, isPlaying: Boolean, stateSong: String) = AudioPlayFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_SONG, song)
                putBoolean(ARG_Is_PLAYING, isPlaying)
                putString(ARG_STATE_SONG, stateSong)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            song = it.getParcelable(ARG_SONG)
            isPlaying = it.getBoolean(ARG_Is_PLAYING)
            stateSong = it.getString(ARG_STATE_SONG)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_audio_play, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        when (stateSong) {
            STATE_NEED_PLAY -> {
                Log.d("xxx", STATE_NEED_PLAY)
                startOrContinueService(ACTION_START, song)
            }

            STATE_PLAYING -> {
                Log.d("xxx", STATE_PLAYING)
                startOrContinueService(ACTION_PLAY, song)
            }
        }

        imgPlay.setOnClickListener(this)
        setRotateImageSong()
    }

    override fun onClick(v: View?) {
        when (v) {
            imgPlay -> {
                isPlaying = if (!isPlaying) {
                    pauseOrResumeAudio(ACTION_PAUSE)
                    true
                } else {
                    pauseOrResumeAudio(ACTION_PLAY)
                    false
                }
            }
        }
    }

    private fun pauseOrResumeAudio(action: String) {
        changeIconAudioPlay()
        val intent = Intent(action, Uri.parse(song?.path), requireContext(), AudioService::class.java)
        intent.putExtra(SONG_URI, song?.path)
        intent.putExtra(SONG_NAME, song?.name)
        intent.putExtra(SONG_ART, song?.artist)
        requireContext().startService(intent)
    }

    private fun setRotateImageSong() {
        val rotateAnimation = RotateAnimation(
                0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f)
        rotateAnimation.interpolator = LinearInterpolator()
        rotateAnimation.duration = 60000
        rotateAnimation.repeatCount = Animation.INFINITE
        imgSong.startAnimation(rotateAnimation)
    }

    private fun initView() {
        isPlaying = true
        changeIconAudioPlay()
        tvSongName.text = song?.name
        tvSongArtist.text = song?.artist
        tvDuration.text = song?.duration
        tvTimePlaying.text = song?.duration
        val imageBitmap = Utils.getCoverPicture(requireContext(), Uri.parse(song?.path))
        if (imageBitmap != null) {
            imgSong.setImageBitmap(imageBitmap)
        } else {
            imgSong.setImageResource(R.drawable.ic_play_circle_outline_black_200dp)
        }
    }

    private fun startOrContinueService(action: String, song: Song?) {
        val intent = Intent(action, Uri.parse(song?.path), requireContext(), AudioService::class.java)
        requireContext().stopService(intent)
        intent.putExtra(Utils.SONG_NAME, song?.name)
        intent.putExtra(Utils.SONG_ART, song?.artist)
        intent.putExtra(Utils.SONG_URI, song?.path)
        requireContext().startService(intent)
    }

    private fun changeIconAudioPlay() {
        if (isPlaying) {
            imgPlay.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp)
            requireContext().stopService(Intent(requireContext(), AudioService::class.java))
        } else {
            imgPlay.setImageResource(R.drawable.ic_play_circle_outline_black_24dp)
        }
    }
}
