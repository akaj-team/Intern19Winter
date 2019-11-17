package asiantech.internship.summer.taplayoutviewpaper

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

            val lastReal = viewPager.getAdapter()!!.getCount() - 2
            if (currPage == 1) {
                viewPager.setCurrentItem(lastReal, false)
            } else if (currPage > lastReal) {
                viewPager.setCurrentItem(1, false)
            }
        }
    }
}
