package asiantech.internship.winter.savedata.ui.editprofile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import asiantech.internship.summer.R
import asiantech.internship.summer.databinding.FragmentEditProfileBinding
import asiantech.internship.winter.savedata.db.TodoDatabase

class EditProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil
                .inflate<FragmentEditProfileBinding>(inflater, R.layout.fragment_edit_profile, container, false)
        val application = requireNotNull(activity?.application)
        val dataSource = TodoDatabase.getInstance(application)
        var idUser = 0L
        activity?.getPreferences(Context.MODE_PRIVATE)?.apply {
            idUser = getLong(getString(R.string.pref_id_user), 0L)
        }
        val viewModelFactory = EditProfileViewModelFactory(idUser, dataSource, application)
        val editProfileViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(EditProfileViewModel::class.java)
        binding.editProfileViewModel = editProfileViewModel

        binding.btnSaveProfile.setOnClickListener {
            editProfileViewModel.getUser().observe(this, Observer {
                it.copy(username = binding.edtFullName.text.toString())
                        .let { user ->
                            editProfileViewModel.update(user)
                        }
            })
            findNavController().navigate(R.id.action_editProfileFragment_to_homeFragment)

        }

        binding.lifecycleOwner = this
        return binding.root
    }
}
