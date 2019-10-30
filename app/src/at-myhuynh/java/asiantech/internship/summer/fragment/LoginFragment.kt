package asiantech.internship.summer.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-myhuynh`.fragment_login.*
import kotlinx.android.synthetic.`at-myhuynh`.fragment_login.edtEmail

class LoginFragment : Fragment() {

    private var mListener: OnReplaceFragmentListener? = null
    private var mFullName: String? = null
    private var mEmail: String? = null
    var onClickListener: ((fragment: Fragment, isAddToBackTack: Boolean) -> Unit) = { _, _ -> }

    companion object {
        const val ARG_USER_NAME = "user"
        const val ARG_EMAIL = "email"

        fun newInstance(fullName: String = "", email: String = "") =
                LoginFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_USER_NAME, fullName)
                        putString(ARG_EMAIL, email)
                    }

                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mFullName = it.getString(ARG_USER_NAME)
            mEmail = it.getString(ARG_EMAIL)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        edtEmail.setText(mEmail)

        /** I */
        /*btnLogin.setOnClickListener {
            (activity as? MainActivity)
                    ?.replaceFragment(EditProfileFragment.newInstance(mFullName, edtEmail.text.toString()), true)
        }

        tvCreateAccount.setOnClickListener {
            (activity as? MainActivity)
                    ?.replaceFragment(SignUpFragment.newInstance(), false)
        }*/

        /** II */
        btnLogin.setOnClickListener {
            onClickListener.invoke(
                    EditProfileFragment.newInstance(mFullName, edtEmail.text.toString()), true)
        }

        tvCreateAccount.setOnClickListener {
            onClickListener.invoke(SignUpFragment.newInstance(), false)
        }

        /** III */
        /*btnLogin.setOnClickListener {
            listener?.onFragmentInteraction(
                    EditProfileFragment.newInstance(mFullName, edtEmail.text.toString()), true)
        }

        tvCreateAccount.setOnClickListener {
            listener?.onFragmentInteraction(SignUpFragment.newInstance(), false)
        }*/
    }

    /** III */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnReplaceFragmentListener) {
            mListener = context
        }
    }
}
