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
    override fun onClick(p0: View?) {
        if (p0 != null) {
            if (p0.id == R.id.tvNext) {
                when (viewPagerStep.currentItem) {
                    0 -> viewPagerStep.setCurrentItem(1)
                    1 -> viewPagerStep.setCurrentItem(2)
                    2 -> startActivity(Intent(this, TabLayoutActivity::class.java))
                }
            }
        }
    }

    private lateinit var viewPagerAdapter: ViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_viewpager)
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        addPagerFragment()
        viewPagerStep.adapter = viewPagerAdapter
        viewPagerStep.addOnPageChangeListener(ViewPagerListener(this::onPageSelected))
        tvNext.setOnClickListener(this)
    }

    private fun addPagerFragment() {
        viewPagerAdapter.addFragment(StepFragment.newInstance("Step 1"), "Fragment Step 1")
        viewPagerAdapter.addFragment(StepFragment.newInstance("Step 2"), "Fragment Step 2")
        viewPagerAdapter.addFragment(StepFragment.newInstance("Step 3"), "Fragment Step 3")
    }

    private fun onPageSelected(position: Int) {
        when (position) {
            0 -> {
                imgFirstDot.setImageResource(R.drawable.enable_position_icon)
                imgSecondDot.setImageResource(R.drawable.disable_position_icon)
                imgThirdDot.setImageResource(R.drawable.disable_position_icon)
            }
            1 -> {
                imgFirstDot.setImageResource(R.drawable.disable_position_icon)
                imgSecondDot.setImageResource(R.drawable.enable_position_icon)
                imgThirdDot.setImageResource(R.drawable.disable_position_icon)
            }
            2 -> {
                imgFirstDot.setImageResource(R.drawable.disable_position_icon)
                imgSecondDot.setImageResource(R.drawable.disable_position_icon)
                imgThirdDot.setImageResource(R.drawable.enable_position_icon)
            }
        }
    }
}
