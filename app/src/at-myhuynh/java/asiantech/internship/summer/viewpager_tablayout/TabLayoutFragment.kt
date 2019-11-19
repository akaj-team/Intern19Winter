package asiantech.internship.summer.viewpager_tablayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-myhuynh`.fragment_tab_layout.*

class TabLayoutFragment : Fragment() {

    private lateinit var listTabs: MutableList<Tab>

    companion object {
        fun newInstance() = TabLayoutFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tab_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initData()
        viewpagerTab.adapter = TabLayoutAdapter(childFragmentManager, listTabs)
        tabLayout.setupWithViewPager(viewpagerTab)
    }

    private fun initData() {
        listTabs = mutableListOf()
        listTabs.apply {
            add(Tab(HomeFragment(), getString(R.string.pagetitle_text_home)))
            add(Tab(InfoFragment(), getString(R.string.pagetitle_text_info)))
            add(Tab(AnotherFragment(), getString(R.string.pagetitle_text_another)))
        }

    }
}
