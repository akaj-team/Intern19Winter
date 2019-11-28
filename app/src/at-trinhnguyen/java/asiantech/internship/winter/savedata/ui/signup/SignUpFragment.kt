package asiantech.internship.winter.savedata.ui.signup

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import asiantech.internship.summer.R
import asiantech.internship.summer.databinding.FragmentSignUpBinding
import asiantech.internship.winter.savedata.db.TodoDatabase
import asiantech.internship.winter.savedata.db.user.User
import asiantech.internship.winter.savedata.isEmail
import asiantech.internship.winter.savedata.ui.ViewModelFactory

class SignUpFragment : Fragment() {

    private lateinit var signUpViewModel: SignUpViewModel
    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_sign_up, container, false)

        val application = requireNotNull(activity?.application)
        val dataSource = TodoDatabase.getInstance(application)
        val viewModelFactory = ViewModelFactory(dataSource, application)
        signUpViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(SignUpViewModel::class.java)
        binding.signUpViewModel = signUpViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvLoginHere.setOnClickListener {
            it.findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }

        binding.btnSignUp.setOnClickListener {
            val username = binding.edtFullName.text
            val email = binding.edtEmail.text
            val password = binding.edtPassword.text
            if (TextUtils.isEmpty(username)) {
                Toast.makeText(context, "Please enter username!", Toast.LENGTH_LONG).show()
            } else if (TextUtils.isEmpty(email) || !email.toString().isEmail()) {
                Toast.makeText(context, "Please enter email!", Toast.LENGTH_LONG).show()
            } else if (TextUtils.isEmpty(password)) {
                Toast.makeText(context, "Please enter password!", Toast.LENGTH_LONG).show()
            } else {
                signUpViewModel.getUsersByEmail(email.toString()).observe(viewLifecycleOwner, Observer {
                    if (it.isNotEmpty()) {
                        Toast.makeText(context, "Email already exists. Please choose a different email !", Toast.LENGTH_LONG).show()
                    } else {
                        signUpViewModel.insertUser(User(0, username.toString(), email.toString(), password.toString()))
                        findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToLoginFragment(email.toString()))
                    }
                })
            }
        }

    }
}
