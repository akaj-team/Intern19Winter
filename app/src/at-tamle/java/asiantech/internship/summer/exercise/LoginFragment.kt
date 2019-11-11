package asiantech.internship.summer.exercise


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-tamle`.fragment_login.*
import kotlinx.android.synthetic.`at-tamle`.fragment_login.edtEmail


class LoginFragment : Fragment() {
    private var mName: String? = null
    private var mEmail: String? = null


    companion object {

        const val ARG_EMAIL = "email"
        const val ARG_NAME = "name"
        fun newInstance(name: String = "", email: String = ""): LoginFragment {
            return LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_NAME, name)
                    putString(ARG_EMAIL, email)

                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mName = it.getString(ARG_NAME)
            mEmail = it.getString(ARG_EMAIL)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edtEmail.setText(mEmail)

        btnLogin.setOnClickListener {
            (activity as? MainSignActivity)
                    ?.replaceFragment(ProfileFragment.newInstance(mName, edtEmail.text.toString()))
        }
        tvAccount.setOnClickListener {
            (activity as? MainSignActivity)
                    ?.replaceFragment(ProfileFragment.newInstance())
        }


    }


}