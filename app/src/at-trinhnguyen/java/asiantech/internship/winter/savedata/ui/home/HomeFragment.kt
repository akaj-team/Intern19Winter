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
import asiantech.internship.winter.savedata.db.TodoDatabase
import asiantech.internship.winter.savedata.db.todo.Todo
import asiantech.internship.winter.savedata.ui.ViewModelFactory
import kotlinx.android.synthetic.`at-trinhnguyen`.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private val idUser = 111L

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val onBackPressedCallback = object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//            }
//        }
//        requireActivity().onBackPressedDispatcher
//                .addCallback(this, onBackPressedCallback)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_home, container, false)
        val application = requireNotNull(activity?.application)
        val dataSource = TodoDatabase.getInstance(application)
        val viewModelFactory = ViewModelFactory(dataSource, application)
        homeViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(HomeViewModel::class.java)
        binding.homeViewModel = homeViewModel


        val navController = findNavController()
        NavigationUI.setupWithNavController(binding.navView, navController)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.apply {
            getString("title")?.let { title ->
                getString("description")?.let { description ->
                    Todo(0, idUser, title
                            , description, false)
                }
            }?.let { homeViewModel.insert(it) }
        }

        val viewPagerAdapter = fragmentManager?.let { context?.let { it1 -> ViewPagerAdapter(it, it1) } }
        binding.viewPager.adapter = viewPagerAdapter
        binding.tabLayout.setupWithViewPager(viewPager)
    }
}
