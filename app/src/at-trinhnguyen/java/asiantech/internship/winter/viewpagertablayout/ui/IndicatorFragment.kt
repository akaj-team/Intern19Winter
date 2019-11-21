package asiantech.internship.winter.viewpagertablayout.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import asiantech.internship.winter.viewpagertablayout.Page
import asiantech.internship.winter.viewpagertablayout.adapter.TabLayoutPagerAdapter
import kotlinx.android.synthetic.`at-trinhnguyen`.fragment_indicator.*
import me.relex.circleindicator.CircleIndicator

class IndicatorFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = IndicatorFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_indicator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pages = mutableListOf<Page>()
        pages.apply {
            add(Page(StepOneFragment(), ""))
            add(Page(StepTwoFragment(), ""))
            add(Page(StepThreeFragment(), ""))
        }
        val adapterViewPager = TabLayoutPagerAdapter(childFragmentManager, pages)
        viewPager.adapter = adapterViewPager
        val indicator = view.findViewById<CircleIndicator>(R.id.indicator)
        indicator.setViewPager(viewPager)

        tvNext.setOnClickListener {
            if (viewPager.currentItem == pages.size - 1) {
                fragmentManager?.apply {
                    beginTransaction().replace(R.id.container, TabLayoutFragment.newInstance(), null)
                            .addToBackStack(null)
                            .commit()
                }
            }
            viewPager.currentItem = viewPager.currentItem + 1
        }
    }
}
