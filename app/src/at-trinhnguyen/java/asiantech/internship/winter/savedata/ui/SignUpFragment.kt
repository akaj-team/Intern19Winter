package asiantech.internship.winter.savedata.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import asiantech.internship.summer.R
import asiantech.internship.summer.databinding.FragmentSignUpBinding
import asiantech.internship.winter.layouteditor.SignUpFragment


class SignUpFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = SignUpFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentSignUpBinding>(
                inflater, R.layout.fragment_sign_up, container, false)

        binding.tvLoginHere.setOnClickListener {
            it.findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }

        return binding.root
    }
}
