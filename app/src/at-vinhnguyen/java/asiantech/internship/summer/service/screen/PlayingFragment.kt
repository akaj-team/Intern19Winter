package asiantech.internship.summer.service.screen

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R

class PlayingFragment : Fragment() {

    companion object {
        val mediaPlayer = MediaPlayer()
        const val SONG_ID = "song_id"

        fun newInstance(id: Int): PlayingFragment {
            val bundle = Bundle()
            bundle.putInt(SONG_ID, id)
            val playerFragment = PlayingFragment()
            playerFragment.arguments = bundle
            return playerFragment
        }
    }

    var currentSongId: Int? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_playing, container, false)
        currentSongId = arguments?.getInt(SONG_ID, -1)
        if (currentSongId == -1) {
            Toast.makeText(context, "Fail to play", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Song Id : $currentSongId", Toast.LENGTH_SHORT).show()
        }
        return view
    }

}
