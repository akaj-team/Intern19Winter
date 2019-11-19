package asiantech.internship.winter.savedata.ui.login

import android.content.Context
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
import asiantech.internship.summer.databinding.FragmentLoginBinding
import asiantech.internship.winter.savedata.db.TodoDatabase
import asiantech.internship.winter.savedata.db.user.User
import asiantech.internship.winter.savedata.isEmail
import asiantech.internship.winter.savedata.ui.ViewModelFactory

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding
    private val idUser = 111L

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireNotNull(activity?.application)
        val dataSource = TodoDatabase.getInstance(application)
        val viewModelFactory = ViewModelFactory(dataSource, application)
        loginViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(LoginViewModel::class.java)
        binding.loginViewModel = loginViewModel
        loginViewModel.insertUser(User(0, "user1", "user1@gmail.com", "0328820909", "123456"))

        binding.tvCreateAccountHere.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text
            val password = binding.edtPassword.text
            if (TextUtils.isEmpty(email) || !email.toString().isEmail()) {
                Toast.makeText(context, "Please enter email!", Toast.LENGTH_LONG).show()
            } else if (TextUtils.isEmpty(password)) {
                Toast.makeText(context, "Please enter password!", Toast.LENGTH_LONG).show()
            } else {
                loginViewModel.login(email.toString(), password.toString()).observe(viewLifecycleOwner, Observer {
                    if (it.isNotEmpty()) {
                        activity?.getPreferences(Context.MODE_PRIVATE)?.edit()
                                ?.apply {
                                    putBoolean("PREF_IS_LOGIN", true)
                                    putLong("PREF_ID_USER", it[0].idUser)
                                    apply()
                                }
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    } else {
                        Toast.makeText(context, "Email or Password is incorrect!", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }

        binding.lifecycleOwner = this
    }
}
