package asiantech.internship.summer.broadcastreceiver.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import asiantech.internship.summer.R
import asiantech.internship.summer.broadcastreceiver.adapter.ListSongAdapter
import asiantech.internship.summer.broadcastreceiver.model.SongModel
import kotlinx.android.synthetic.`at-nhatnguyen`.fragment_list.*

class ListSongFragment : Fragment(){

    companion object{
         fun newInstance() = ListSongFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val listSong = mutableListOf<SongModel>()

//        for (i in 1..6){
//            listSong.add(SongModel("Song $i"))
//        }

        listSong.add(SongModel("nnnnnnnnnnnnnnnnnnn"))
        listSong.add(SongModel("aaaaaaaaaaaaa"))
        listSong.add(SongModel("bbbbbbbbbbbbbb"))
        val adapter = ListSongAdapter(listSong)
        rvList.layoutManager = LinearLayoutManager(activity)
        rvList.adapter = adapter
    }
}
