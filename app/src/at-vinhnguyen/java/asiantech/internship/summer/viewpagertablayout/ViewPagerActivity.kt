package asiantech.internship.summer.viewpagertablayout

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-vinhnguyen`.activity_viewpager.*

class ViewPagerActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var adapterViewPager: ViewPagerAdapter
    private lateinit var fragmentViewPagerObjectList: MutableList<FragmentViewPagerObject>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_viewpager)
        initData()
        initAdapter()
        initViews()
    }

    override fun onClick(p0: View?) {
        if (p0 != null) if (p0.id == R.id.tvNext) {
            when (viewPagerStep.currentItem) {
                0 -> viewPagerStep.currentItem++
                1 -> viewPagerStep.currentItem++
                2 -> startActivity(Intent(this, TabLayoutActivity::class.java))
            }
        }
    }

    private fun onPageSelected(position: Int) {
        indicator.animatePageSelected(position)
    }

    private fun initData() {
        fragmentViewPagerObjectList = arrayListOf(FragmentViewPagerObject(StepFragment.newInstance("Step 1"), "Fragment Step 1"),
                FragmentViewPagerObject(StepFragment.newInstance("Step 2"), "Fragment Step 2"),
                FragmentViewPagerObject(StepFragment.newInstance("Step 3"), "Fragment Step 3"))
    }

    private fun initAdapter() {
        adapterViewPager = ViewPagerAdapter(supportFragmentManager, fragmentViewPagerObjectList)
        viewPagerStep.adapter = adapterViewPager
        indicator.setViewPager(viewPagerStep)
        viewPagerStep.addOnPageChangeListener(ViewPagerListener(this::onPageSelected))
    }

    private fun initViews() {
        tvNext.setOnClickListener(this)
    }
}
