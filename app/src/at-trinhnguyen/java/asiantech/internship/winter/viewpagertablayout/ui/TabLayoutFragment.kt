package asiantech.internship.winter.viewpagertablayout.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import asiantech.internship.winter.viewpagertablayout.Page
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

        val pages = mutableListOf<Page>()
        pages.apply {
            add(Page(HomeFragment(), getString(R.string.tablayout_title_home)))
            add(Page(InfoFragment(), getString(R.string.tablayout_title_info)))
            add(Page(AnotherFragment(), getString(R.string.tablayout_title_another)))
        }
        val adapterViewPager = TabLayoutPagerAdapter(childFragmentManager, pages)

        viewPager.adapter = adapterViewPager
        tabLayout.setupWithViewPager(viewPager)
    }
}
