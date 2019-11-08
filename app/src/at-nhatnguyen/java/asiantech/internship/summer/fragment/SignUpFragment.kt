package asiantech.internship.summer.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-nhatnguyen`.fragment_sign_up.*
import kotlinx.android.synthetic.`at-nhatnguyen`.fragment_sign_up.edtEmail
import kotlinx.android.synthetic.`at-nhatnguyen`.fragment_sign_up.edtPassword

class SignUpFragment : Fragment() {

    companion object {
        fun newInstance() = SignUpFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnCreateAccount.setOnClickListener {
            val fullName = edtFullName.text.toString()
            val email = edtEmail.text.toString()
            val pass = edtPassword.text.toString()
            fragmentManager?.beginTransaction()
                    ?.replace(R.id.flSign, LoginFragment.newInstance(email, pass, fullName))
                    ?.addToBackStack(null)
                    ?.commit()
        }
        tvLoginHere.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.flSign, LoginFragment())
                    ?.addToBackStack(null)
                    ?.commit()
        }
    }
}
