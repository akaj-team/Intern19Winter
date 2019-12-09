package asiantech.internship.summer.broadcastreceiver.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import asiantech.internship.summer.broadcastreceiver.Service.MusicService
import asiantech.internship.summer.broadcastreceiver.model.SongModel
import asiantech.internship.summer.broadcastreceiver.model.Utils
import kotlinx.android.synthetic.`at-nhatnguyen`.fragment_play_mp3.*

class PlayMp3Fragment : Fragment() {

    private var ARG_SONG = "songModel"
    private var songModel: SongModel? = null
    private lateinit var intent:Intent

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
        songModel?.let { starService(it) }

        imgPlay.setOnClickListener {
            context?.startService(intent)
        }
    }

    private fun getDuration(duration: Int): String {
        val seconds = duration / 1000 % 60
        val minutes = ((duration - seconds) / 1000 / 60).toLong()
        return String.format("%02d: %02d", minutes, seconds)
    }

    private fun starService(songModel:SongModel){
        intent = Intent(context, MusicService::class.java)
        context?.stopService(intent)
        intent.putExtra("URI",songModel.path.toString())
    }
}