package asiantech.internship.winter.viewpagertablayout.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import asiantech.internship.winter.viewpagertablayout.adapter.TabLayoutPagerAdapter
import kotlinx.android.synthetic.`at-trinhnguyen`.fragment_tab_layout.*

class TabLayoutFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = TabLayoutFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tab_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPagerAdapter = fragmentManager?.let { it ->
            context?.let { context ->
                TabLayoutPagerAdapter(it, context)
            }
        }
        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }
}
