package asiantech.internship.summer.service_broadcast_receiver.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import asiantech.internship.summer.service_broadcast_receiver.Song
import asiantech.internship.summer.service_broadcast_receiver.Utils
import asiantech.internship.summer.service_broadcast_receiver.Utils.Companion.ARG_SONG
import asiantech.internship.summer.service_broadcast_receiver.service.AudioService
import kotlinx.android.synthetic.`at-myhuynh`.fragment_audio_play.*

class AudioPlayFragment : Fragment(), View.OnClickListener {
    private var song: Song? = null
    private var isPlaying = false

    companion object {
        fun newInstance(song: Song) = AudioPlayFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_SONG, song)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            song = it.getParcelable(ARG_SONG)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_audio_play, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        startService(song)

        imgPlay.setOnClickListener(this)
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

    private fun startService(song: Song?) {
        val intent = Intent(requireContext(), AudioService::class.java)
        requireContext().stopService(intent)
        intent.putExtra(Utils.SONG_NAME, song?.name)
        intent.putExtra(Utils.SONG_ART, song?.artist)
        intent.putExtra(Utils.SONG_URI, song?.path)
        requireContext().startService(intent)
    }

    override fun onClick(v: View?) {
        when (v) {
            imgPlay -> {
                isPlaying = !isPlaying
                changeIconAudioPlay()
            }
        }
    }

    private fun changeIconAudioPlay() {
        if (isPlaying) {
            imgPlay.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp)
        } else {
            imgPlay.setImageResource(R.drawable.ic_play_circle_outline_black_24dp)
        }
    }
}
