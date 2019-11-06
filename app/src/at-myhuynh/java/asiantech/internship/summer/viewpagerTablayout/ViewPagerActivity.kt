package asiantech.internship.summer.viewpagerTablayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import asiantech.internship.summer.R

class ViewPagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        supportFragmentManager.beginTransaction()
                .replace(R.id.flPagerFragment, ViewPagerFragment.newInstance())
                .commit()
    }
}
