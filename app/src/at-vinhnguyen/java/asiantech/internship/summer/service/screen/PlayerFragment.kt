package asiantech.internship.summer.service.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R

class PlayerFragment : Fragment() {
    companion object {

        const val SONG_ID = "song_id"

        fun newInstance(id: Int): PlayerFragment {
            val bundle = Bundle()
            bundle.putInt(SONG_ID, id)
            val playerFragment = PlayerFragment()
            playerFragment.arguments = bundle
            return playerFragment
        }
    }
    var songId : Int? = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_player, container, false)
        songId = arguments?.getInt(SONG_ID,0)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
