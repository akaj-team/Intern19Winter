package asiantech.internship.winter.savedata.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import asiantech.internship.summer.R
import asiantech.internship.summer.databinding.FragmentLoginBinding
import asiantech.internship.winter.layouteditor.LoginFragment

class LoginFragment : Fragment() {

    private var mName: String? = null
    private var mEmail: String? = null
    var callBack: ((String, Int) -> Unit)? = null

    companion object {
        private const val ARG_NAME = "arg_name"
        private const val ARG_EMAIL = "arg_email"
        private val instance = LoginFragment()
        @JvmStatic
        fun newInstance(name: String, email: String) =
                instance.apply {
                    arguments = Bundle().apply {
                        putString(ARG_NAME, name)
                        putString(ARG_EMAIL, email)
                    }
                }

        fun getInstance() = instance
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //get data from fragment sign up
        arguments?.let {
            mName = it.getString(ARG_NAME)
            mEmail = it.getString(ARG_EMAIL)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(
                inflater, R.layout.fragment_login, container, false)

        binding.tvCreateAccountHere.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        binding.btnLogin.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_todoFragment)
        }
        return binding.root
    }
}
