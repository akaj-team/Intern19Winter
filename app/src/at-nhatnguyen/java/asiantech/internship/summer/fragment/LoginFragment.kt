package asiantech.internship.summer.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-nhatnguyen`.fragment_login.*

class LoginFragment : Fragment() {

    companion object {
        private const val ARG_PARA1 = "email"
        private const val ARG_PARA2 = "pass"
        private const val ARG_PARA3 = "fullName"
        fun newInstance(email: String, pass: String, fullName: String) = LoginFragment()
                .apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARA1, email)
                        putString(ARG_PARA2, pass)
                        putString(ARG_PARA3, fullName)
                    }
                }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments.let {
            //fullName2 = it?.getString(SignUpFragment().ARG_PARA3)
            edtEmail.setText(it?.getString(ARG_PARA1))
            edtPassword.setText(it?.getString(ARG_PARA2))
            edtNotShow.setText(it?.getString(ARG_PARA3))
        }
        btnLogin.setOnClickListener {
            fragmentManager?.beginTransaction()
                    ?.replace(R.id.flSign, EditProfileFragment.newInstance(edtEmail.text.toString(), edtPassword.text.toString(), edtNotShow.text.toString()))
                    ?.addToBackStack(null)
                    ?.commit()
        }
        tvCreateAccount.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.flSign, SignUpFragment())
                    ?.addToBackStack(null)
                    ?.commit()
        }
    }
}
