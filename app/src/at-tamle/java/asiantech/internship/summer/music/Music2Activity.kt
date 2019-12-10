package asiantech.internship.summer.music

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import asiantech.internship.summer.R

class Music2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music2)

        //replace hoac add Listsongfragment vô
        supportFragmentManager.beginTransaction().add(R.id.frMusic, ListSongFragment.newInstance()).commit()
    }
}
