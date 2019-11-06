package asiantech.internship.summer.viewpagerTablayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-myhuynh`.fragment_view_pager.*

class ViewPagerFragment : Fragment() {

    companion object {
        private val instance = ViewPagerFragment()
        fun newInstance() = instance
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewpager.adapter = context?.let { ViewPagerAdapter(it) }
        tabDots.setupWithViewPager(viewpager, true)

        setTextView(tabDots.selectedTabPosition)

        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                setTextView(position)
            }

            override fun onPageSelected(position: Int) {}
        })

        tvSkip.setOnClickListener {
            fragmentManager?.beginTransaction()
                    ?.replace(R.id.flPagerFragment, TabLayoutFragment())
                    ?.addToBackStack(TabLayoutFragment().javaClass.simpleName)
                    ?.commit()
        }
    }

    private fun setTextView(position: Int) {
        if (position < 2) {
            tvSkip.text = getString(R.string.textview_text_skip)
        } else {
            tvSkip.text = getString(R.string.textview_text_next)
        }
    }
}
