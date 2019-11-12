package asiantech.internship.summer.viewpagertablayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-vinhnguyen`.activity_tablayout.*

class TabLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tablayout)
        val adapter = ViewPagerAdapter(supportFragmentManager)
        val listImage = mutableListOf(R.drawable.tablayout1, R.drawable.tablayout2, R.drawable.tablayout3, R.drawable.tablayout4, R.drawable.tablayout5, R.drawable.tablayout6)
        listImage.shuffle()
        adapter.addFragment(TabFragment.newInstance(listImage[0]), "HOME")
        adapter.addFragment(TabFragment.newInstance(listImage[1]), "INFO")
        adapter.addFragment(TabFragment.newInstance(listImage[2]), "ANOTHER")
        viewPagerTabLayout.adapter = adapter
        tabLayout.setupWithViewPager(viewPagerTabLayout)
    }
}
