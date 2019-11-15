package asiantech.internship.winter.savedata.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import asiantech.internship.summer.R
import asiantech.internship.summer.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.`at-trinhnguyen`.fragment_home.*

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home, container, false)
        val navController = findNavController()
        NavigationUI.setupWithNavController(binding.navView, navController)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPagerAdapter = fragmentManager?.let { context?.let { it1 -> ViewPagerAdapter(it, it1) } }
        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
