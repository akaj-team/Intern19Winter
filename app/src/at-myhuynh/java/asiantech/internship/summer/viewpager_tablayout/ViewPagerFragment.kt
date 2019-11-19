package asiantech.internship.summer.viewpager_tablayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-myhuynh`.fragment_view_pager.*

class ViewPagerFragment : Fragment() {

    private lateinit var listTabs: MutableList<Tab>

    companion object {
        fun newInstance() = ViewPagerFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        viewpager.adapter = ViewPagerAdapter(childFragmentManager, listTabs)
        indicator.setViewPager(viewpager)

        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                setTextView(position)
            }

            override fun onPageSelected(position: Int) {}
        })

        tvSkip.setOnClickListener {
            (activity as? ViewPagerActivity)?.replaceFragment(TabLayoutFragment.newInstance(), true)
        }
    }

    private fun setTextView(position: Int) {
        if (position < 2) {
            tvSkip.text = getString(R.string.textview_text_skip)
        } else {
            tvSkip.text = getString(R.string.textview_text_next)
        }
    }

    private fun initData() {
        listTabs = mutableListOf()
        listTabs.apply {
            add(Tab(StepOneFragment(), null))
            add(Tab(StepTwoFragment(), null))
            add(Tab(StepThreeFragment(), null))
        }
    }
}
