package asiantech.internship.summer.service_broadcast_receiver

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import asiantech.internship.summer.service_broadcast_receiver.fragment.ListSongFragment

class MusicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)
        repalceFragment(ListSongFragment.newInstance(), false)
    }

    fun repalceFragment(fragment: Fragment, isAddToBackStack: Boolean) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(fragment.javaClass.simpleName)
        }
        fragmentTransaction.replace(R.id.flFragment, fragment)
        fragmentTransaction.commit()
    }
}
