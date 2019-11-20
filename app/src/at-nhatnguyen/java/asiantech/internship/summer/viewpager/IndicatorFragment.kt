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
        fun newInstance() = IndicatorFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_indicator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listFragment = mutableListOf<Fragment>()
        listFragment.apply {
            add(StepOneFragment())
            add(StepTwoFragment())
            add(StepThreeFragment())
        }

        val adapterViewPager = IndicatorAdapter(childFragmentManager,listFragment)
        viewPager.adapter = adapterViewPager
        tabLayout.setupWithViewPager(viewPager)
        tvNext.setOnClickListener {
            viewPager.currentItem = viewPager.currentItem + 1
            if (viewPager.currentItem == 2) {
                tvNext.setOnClickListener {
                    fragmentManager?.beginTransaction()?.
                            replace(R.id.frameLayout, TabLayoutFragment.newInstance())?.
                            addToBackStack(null)?.
                            commit()
                }
            }
        }
    }
}
