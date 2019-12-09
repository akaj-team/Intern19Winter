package asiantech.internship.summer.music

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-tamle`.fragment_list_song.*

class Music2Activity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var listSong: MutableList<SongModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music2)
        linearLayoutManager = LinearLayoutManager(this)
        listmusic.layoutManager = linearLayoutManager
        val recyclerViewAdapter = SongAdapter(listSong, this)
        listmusic.adapter = recyclerViewAdapter

        initData()
    }

    private fun initData() {
        listSong = mutableListOf(
                SongModel("bai_hat_1"), SongModel("bai_hat_2"),SongModel("bai_hat_3")
        )
    }
}
