package asiantech.internship.winter.viewpagertablayout.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import asiantech.internship.winter.viewpagertablayout.adapter.IndicatorPagerAdapter
import kotlinx.android.synthetic.`at-trinhnguyen`.fragment_indicator.*

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

        val viewPagerAdapter = context?.let { IndicatorPagerAdapter(it) }
        viewPager.adapter = viewPagerAdapter

        tabLayout.setupWithViewPager(viewPager, true)

        tvNext.setOnClickListener {
            when (viewPager.currentItem) {
                0 -> viewPager.currentItem = 1
                1 -> viewPager.currentItem = 2
                2 -> fragmentManager?.apply {
                    beginTransaction().replace(R.id.container, TabLayoutFragment.newInstance(), null)
                            .addToBackStack(null)
                            .commit()
                }
            }
        }
    }
}
