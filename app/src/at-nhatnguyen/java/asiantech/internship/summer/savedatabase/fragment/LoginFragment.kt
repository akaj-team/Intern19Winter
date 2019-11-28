package asiantech.internship.summer.savedatabase.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import asiantech.internship.summer.savedatabase.database.DBHandling
import asiantech.internship.summer.savedatabase.database.UserModel
import kotlinx.android.synthetic.`at-nhatnguyen`.fragment_login.*

class LoginFragment : Fragment() {

    private lateinit var userHandling: DBHandling

    companion object {
        private const val ARG_PARAM_1 = "email"
        private const val ARG_PARAM_2 = "pass"
        fun newInstance(email: String, password: String) = LoginFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM_1, email)
                putString(ARG_PARAM_2, password)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edtEmail.setText(arguments?.getString(ARG_PARAM_1))
        edtPassword.setText(arguments?.getString(ARG_PARAM_2))

        btnLogin.setOnClickListener {
            userHandling = DBHandling(requireContext())
            val email = edtEmail.text.toString()
            val pass = edtPassword.text.toString()
            val userLogin = checkLogin(email, pass)
            if (userLogin != null) {
                fragmentManager?.beginTransaction()?.replace(R.id.frameLayoutActivity, ViewPagerFragment.newInstance(userLogin.userId))?.commit()
            } else {
                Toast.makeText(activity, "Email or password incorrect", Toast.LENGTH_SHORT).show()
            }
        }
        tvCreateAccount.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.frameLayoutActivity, RegisterFragment.newInstance())?.addToBackStack(null)?.commit()
        }
    }

    private fun checkLogin(email: String, password: String): UserModel? {
        val user = userHandling.checkLogin(email, password)
        return if (user.size > 0) {
            user[0]
        } else {
            null
        }
    }
}
