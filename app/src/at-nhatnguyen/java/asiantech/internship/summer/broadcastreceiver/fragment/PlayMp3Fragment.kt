package asiantech.internship.summer.broadcastreceiver.fragment

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import asiantech.internship.summer.broadcastreceiver.Service.MusicService
import asiantech.internship.summer.broadcastreceiver.model.SongModel
import asiantech.internship.summer.broadcastreceiver.model.Utils
import kotlinx.android.synthetic.`at-nhatnguyen`.fragment_play_mp3.*
import kotlin.collections.ArrayList


class PlayMp3Fragment : Fragment() {

    private lateinit var rotate: ObjectAnimator
    private var musicBound = false
    private var position: Int? = 0
    private var ARG_SONG = "songModel"
    private val LIST_SONG = "listSong"
    private val POSITION = "position"
    private var songModel: SongModel? = null
    private var playIntent: Intent? = null
    private var musicService = MusicService()
    private var isPlay = false

    private lateinit var listSong: ArrayList<SongModel>

    companion object {

        fun newInstance(listSong: ArrayList<SongModel>, position: Int, songModel: SongModel) = PlayMp3Fragment().apply {
            arguments = Bundle().apply {
                putParcelableArrayList(LIST_SONG, listSong)
                putInt(POSITION, position)
                putParcelable(ARG_SONG, songModel)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(asiantech.internship.summer.R.layout.fragment_play_mp3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        listSong = arguments?.getParcelableArrayList(LIST_SONG)!!
        position = arguments?.getInt(POSITION)
        Log.d("xxx", "${listSong.size}")
        songModel = arguments?.getParcelable(ARG_SONG)
        // val uri = Uri.parse(songModel?.path)
        val songModel = listSong[position!!]


        tvSongName.text = songModel.songName
        // tvSongName.text = songModel?.songName
        tvEndTime.text = getDuration(songModel.duration)
        val bitmap = context?.let { Utils.songImg(it, Uri.parse(songModel.path)) }
        circleImageView.setImageBitmap(bitmap)
        if (bitmap == null) {
            circleImageView.setImageResource(asiantech.internship.summer.R.drawable.ic_music_background)
        }


        //seekBar()
        control()

        rotate = ObjectAnimator.ofFloat(circleImageView, "rotation", 0f, 360f).apply {
            duration = 6000
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.RESTART
            interpolator = LinearInterpolator()
            start()
        }

    }

    private fun getDuration(duration: Int): String {
        val seconds = duration / 1000 % 60
        val minutes = ((duration - seconds) / 1000 / 60).toLong()
        return String.format("%02d: %02d", minutes, seconds)
    }

    private fun control() {
        imgPlay.setOnClickListener {
            if (isPlay == false) {
                musicService.pause()
                imgPlay.setImageResource(asiantech.internship.summer.R.drawable.ic_play_arrow)
                isPlay = true
                rotate.pause()
            } else {
                musicService.start()
                imgPlay.setImageResource(asiantech.internship.summer.R.drawable.ic_pause_circle_filled)
                isPlay = false
                rotate.resume()
            }
        }

        imgNexSong.setOnClickListener {
            nexSong()
        }

        imgPreviousSong.setOnClickListener {
            prev()
        }
    }

    private var musicConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            musicBound = false
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MusicService.MusicBinder

            musicService = binder.getService
            musicService.setList(listSong)
            position?.let { musicService.setSongPosition(it) }
            musicService.playSong()
            musicBound = true
        }
    }

    override fun onStart() {
        super.onStart()
        if (playIntent == null) {
            playIntent = Intent(context, MusicService::class.java)
            context?.bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.stopService(playIntent)
    }

    private fun nexSong() {
        if (isPlay == false){
            musicService.playNext()
            setSongData()
        }else{
            musicService.playNext()
            setSongData()
            imgPlay.setImageResource(asiantech.internship.summer.R.drawable.ic_pause_circle_filled)
            rotate.resume()
            isPlay = false
        }
    }

    private fun prev() {
        if (isPlay == false){
            musicService.playPrev()
            setSongData()
        }else{
            musicService.playPrev()
            setSongData()
            imgPlay.setImageResource(asiantech.internship.summer.R.drawable.ic_pause_circle_filled)
            rotate.resume()
            isPlay = false
        }
    }

    private fun setSongData(){
        val model = listSong[musicService.getPosition()]
        tvSongName.text = model.songName
        val endTime = model.duration
        tvEndTime.text = getDuration(endTime)
        val imgBitmap = context?.let { Utils.songImg(it, Uri.parse(model.path)) }
        circleImageView.setImageBitmap(imgBitmap)
        if (imgBitmap == null) {
            circleImageView.setImageResource(asiantech.internship.summer.R.drawable.ic_music_background)
        }
    }

    private fun seekBar() {

        val startSeekBar = object : Thread() {
            override fun run() {
                super.run()
                val total = musicService.getDuration()
                var currentPosition = 0
                while (currentPosition < total!!) {
                    try {
                        sleep(500)
                        currentPosition = musicService.getCurrentPosition()!!
                        seekBar.progress = currentPosition

                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            }
        }
        startSeekBar.start()
        seekBar.max = musicService.getDuration()!!
//        mediaPlayer?.setOnPreparedListener {
//            startSeekBar.start()
//            seekBar.max = musicService.getDuration()!!
//        }
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    musicService.seekBar(progress)
                    // mediaPlayer?.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }
}
