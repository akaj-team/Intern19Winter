package asiantech.internship.winter.savedata.ui.home

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import asiantech.internship.summer.R
import asiantech.internship.summer.databinding.FragmentHomeBinding
import asiantech.internship.winter.savedata.db.TodoDatabase
import kotlinx.android.synthetic.`at-trinhnguyen`.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var drawerLayout: DrawerLayout
    private var idUser = 0L

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_home, container, false)
        val application = requireNotNull(activity?.application)
        val dataSource = TodoDatabase.getInstance(application)
        activity?.getPreferences(Context.MODE_PRIVATE)?.apply {
            idUser = getLong(getString(R.string.pref_id_user), 0L)
        }
        val viewModelFactory = HomeViewModelFactory(idUser, dataSource, application)
        homeViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(HomeViewModel::class.java)
        binding.homeViewModel = homeViewModel

        val viewPagerAdapter = childFragmentManager.let { context?.let { context -> ViewPagerAdapter(it, context) } }
        binding.viewPager.adapter = viewPagerAdapter

        homeViewModel.getUser().observe(this, Observer {
            binding.navView.getHeaderView(0).apply {
                findViewById<TextView>(R.id.tvNickName).text = it.username
                findViewById<TextView>(R.id.tvEmail).text = it.email
                findViewById<ImageView>(R.id.imgAvatar).setImageURI(Uri.parse(it.avatarPath))
            }
        })

        drawerLayout = binding.drawerLayout
        val navController = findNavController()
        NavigationUI.setupWithNavController(binding.navView, navController)

        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.logout -> {
                    activity?.getPreferences(Context.MODE_PRIVATE)?.edit()
                            ?.apply {
                                putBoolean(getString(R.string.pref_is_login), false)
                                putLong(getString(R.string.pref_id_user), 0L)
                                apply()
                            }
                    findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
                }
                R.id.editProfile -> {
                    drawerLayout.closeDrawers()
                    findNavController().navigate(R.id.action_homeFragment_to_editProfileFragment)
                }
            }
            true
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tabLayout.setupWithViewPager(viewPager)
    }
}
