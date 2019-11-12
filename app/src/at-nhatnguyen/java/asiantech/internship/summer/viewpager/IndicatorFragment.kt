package asiantech.internship.summer.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-nhatnguyen`.fragment_indicator.*

class IndicatorFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = IndicatorFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_indicator, container, false)
    }

    //truyen adapter indicator
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPageAdapter = context?.let {
            IndicatorAdapter(it)
        }
        viewPager.adapter = viewPageAdapter
        tabLayout.setupWithViewPager(viewPager)
        tvNext.setOnClickListener {
            when (viewPager.currentItem) {
                0 -> viewPager.currentItem = 1
                1 -> viewPager.currentItem = 2
                2 -> viewPager.currentItem.apply {
                    fragmentManager?.beginTransaction()?.replace(R.id.frameLayout, TabLayoutFragment.newInstance())?.addToBackStack(null)?.commit()
                }
            }
        }
    }
}
