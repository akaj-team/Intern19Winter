package asiantech.internship.winter.viewpagertablayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import asiantech.internship.summer.R
import asiantech.internship.winter.viewpagertablayout.ui.IndicatorFragment

class ViewPagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        supportFragmentManager.apply {
            beginTransaction().replace(R.id.container, IndicatorFragment.newInstance(), null)
                    .addToBackStack(null)
                    .commit()
        }
    }
}
