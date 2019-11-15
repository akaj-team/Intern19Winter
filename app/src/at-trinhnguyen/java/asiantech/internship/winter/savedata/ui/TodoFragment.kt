package asiantech.internship.winter.savedata.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import asiantech.internship.summer.R
import asiantech.internship.summer.databinding.FragmentTodoBinding

class TodoFragment : Fragment() {

    private lateinit var drawerLayout: DrawerLayout

    companion object {
        fun newInstance() = TodoFragment()
    }

    private lateinit var viewModel: TodoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentTodoBinding>(inflater, R.layout.fragment_todo, container, false)
        val navController = findNavController()
        NavigationUI.setupWithNavController(binding.navView, navController)
        drawerLayout = binding.drawerLayout
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TodoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
