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



    var songId: Int? = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_playing, container, false)
        songId = arguments?.getInt(SONG_ID, 0)
        if (songId == 0) {
            Toast.makeText(context, "Fail to play", Toast.LENGTH_SHORT).show()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
