package asiantech.internship.summer.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-nhatnguyen`.fragment_tablayout.*

class TabLayoutFragment : Fragment() {

    companion object {
        fun newInstance() = TabLayoutFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tablayout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listModel = mutableListOf<Model>()
        listModel.apply {
            add(Model(FirstTabFragment(),getString(R.string.tab_one)))
            add(Model(SecondTabFragment(),getString(R.string.tab_two)))
            add(Model(ThirdTabFragment(),getString(R.string.tab_three)))
        }

        val adapter = fragmentManager?.let {
            TabLayoutAdapter(it, listModel)
        }
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }
}
