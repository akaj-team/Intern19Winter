package asiantech.internship.summer.broadcastreceiver

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import asiantech.internship.summer.R
import asiantech.internship.summer.broadcastreceiver.fragment.ListSongFragment

class MusicActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)
        supportFragmentManager.beginTransaction().add(R.id.frlActivity, ListSongFragment.newInstance()).commit()


    }


}
