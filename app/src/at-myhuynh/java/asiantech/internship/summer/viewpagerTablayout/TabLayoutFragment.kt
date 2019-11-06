package asiantech.internship.summer.viewpagerTablayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-myhuynh`.fragment_tab_layout.*

class TabLayoutFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tab_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewpagerTab.adapter = fragmentManager?.let { it ->
            context?.let { context ->
                TabLayoutAdapter(it, context)
            }
        }
        tabLayout.setupWithViewPager(viewpagerTab)
    }
}
