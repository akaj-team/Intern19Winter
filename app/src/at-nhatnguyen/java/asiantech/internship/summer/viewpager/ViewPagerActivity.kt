package asiantech.internship.summer.viewpager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import asiantech.internship.summer.R

class ViewPagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
        supportFragmentManager.beginTransaction().
                add(R.id.frameLayout, IndicatorFragment.newInstance()).
                commit()
    }
}
