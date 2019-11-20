package asiantech.internship.winter.savedata.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
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
import asiantech.internship.winter.savedata.db.todo.Todo
import asiantech.internship.winter.savedata.db.user.User
import asiantech.internship.winter.savedata.ui.ViewModelFactory
import kotlinx.android.synthetic.`at-trinhnguyen`.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var drawerLayout: DrawerLayout
    private var idUser = 0L
    private var user = User()

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

        activity?.getPreferences(Context.MODE_PRIVATE)?.apply {
            idUser = getLong(getString(R.string.pref_id_user), 0L)
        }


        drawerLayout = binding.drawerLayout
        val navController = findNavController()
        NavigationUI.setupWithNavController(binding.navView, navController)
        binding.navView.getHeaderView(0).apply {
            findViewById<TextView>(R.id.tvNickName).text = user.username
            findViewById<TextView>(R.id.tvEmail).text = user.email
        }

        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.logout -> {
                    Toast.makeText(context, "aa", Toast.LENGTH_LONG).show()
                    activity?.getPreferences(Context.MODE_PRIVATE)?.edit()
                            ?.apply {
                                putBoolean("PREF_IS_LOGIN", false)
                                putLong("PREF_ID_USER", 0L)
                                apply()
                            }
                    findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
                }
            }
            true
        }


        homeViewModel.navigateToEditTodo.observe(this, Observer { idUser ->
            idUser?.let {
                this.findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToEditTodoFragment(idUser))
                homeViewModel.onEditTodoNavigated()
            }
        })


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
