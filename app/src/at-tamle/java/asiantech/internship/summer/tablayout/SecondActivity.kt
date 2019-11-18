package asiantech.internship.summer.tablayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import asiantech.internship.summer.R


import kotlinx.android.synthetic.`at-tamle`.activity_first.viewPager
import kotlinx.android.synthetic.`at-tamle`.activity_second.*


class SecondActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        initView()
    }

    private fun initView() {
        val fragments = mutableListOf<Fragment>()
        fragments.add(Tab3Fragment())
        fragments.add(Tab1Fragment())
        fragments.add(Tab2Fragment())
        fragments.add(Tab3Fragment())
        fragments.add(Tab1Fragment())

        viewPager.adapter = ViewPagerAdapter(supportFragmentManager, fragments, listOf("", "HOME", "INFO", "ANOTHER", ""))
        tabLayout.setupWithViewPager(viewPager)

        viewPager.setCurrentItem(1)

        viewPager.addOnPageChangeListener(this)

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
    }

    override fun onPageScrollStateChanged(state: Int) {
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            val currPage = viewPager.getCurrentItem()

            if (currPage == 1) {
                viewPager.setCurrentItem(1, false)
            } else if (currPage == 2) {
                viewPager.setCurrentItem(2, false)
            } else if (currPage == 3) {
                viewPager.setCurrentItem(3, false)
            } else if (currPage == 4) {
                viewPager.setCurrentItem(1, false)
            } else {
                viewPager.setCurrentItem(3, false)
            }
        }
    }
}
