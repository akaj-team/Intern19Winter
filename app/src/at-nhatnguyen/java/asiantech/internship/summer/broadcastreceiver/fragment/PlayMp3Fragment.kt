package asiantech.internship.summer.broadcastreceiver.fragment

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import asiantech.internship.summer.broadcastreceiver.Service.MusicService
import asiantech.internship.summer.broadcastreceiver.model.SongModel
import asiantech.internship.summer.broadcastreceiver.model.Utils
import kotlinx.android.synthetic.`at-nhatnguyen`.fragment_play_mp3.*

class PlayMp3Fragment : Fragment() {

    private var ARG_SONG = "songModel"
    private var songModel: SongModel? = null
    private var playIntent: Intent? = null
    private var musicService = MusicService()
    private var isPlay = false
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var listSong: MutableList<SongModel>

    companion object {
        fun newInstance(songModel: SongModel) = PlayMp3Fragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_SONG, songModel)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_play_mp3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        songModel = arguments?.getParcelable(ARG_SONG)
        tvSongName.text = songModel?.songName
        tvEndTime.text = songModel?.duration?.let { getDuration(it) }
        val bitmap = context?.let { Utils.songImg(it, Uri.parse(songModel?.path)) }
        circleImageView.setImageBitmap(bitmap)
        if (bitmap == null) {
            circleImageView.setImageResource(R.drawable.ic_music_background)
        }
        val uri = Uri.parse(songModel?.path)
        mediaPlayer = MediaPlayer.create(context, uri)
        mediaPlayer.start()
        seekBar()
        control()
    }

    private fun getDuration(duration: Int): String {
        val seconds = duration / 1000 % 60
        val minutes = ((duration - seconds) / 1000 / 60).toLong()
        return String.format("%02d: %02d", minutes, seconds)
    }

    private fun doPrev() {
        musicService.playPrev()
    }

    private fun next() {
        musicService.playNext()
    }

    private fun seekBar() {
        val startSeekBar = object : Thread() {
            override fun run() {
                super.run()
                val total = mediaPlayer.duration
                var currentPosition = 0
                while (currentPosition < total) {
                    try {
                        sleep(500)
                        currentPosition = mediaPlayer.currentPosition
                        seekBar.progress = currentPosition

                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            }
        }
        mediaPlayer.setOnPreparedListener {
            startSeekBar.start()
            seekBar.max = mediaPlayer.duration
        }
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
    }

    private fun control() {
        imgPlay.setOnClickListener {
            if (isPlay == false) {
                mediaPlayer.pause()
                imgPlay.setImageResource(R.drawable.ic_play_arrow)
                isPlay = true
            } else {
                mediaPlayer.start()
                imgPlay.setImageResource(R.drawable.ic_pause_circle_filled)
                isPlay = false
            }
        }

        imgNexSong.setOnClickListener {
            //mediaPlayer.reset()
          //  next()
        }

        imgPreviousSong.setOnClickListener {
            doPrev()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        playIntent?.let { activity?.stopService(playIntent) }
        activity?.stopService(playIntent)
    }
}
