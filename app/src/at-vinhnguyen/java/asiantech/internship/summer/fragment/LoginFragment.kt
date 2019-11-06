package asiantech.internship.summer.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-vinhnguyen`.fragment_login.*

class LoginFragment : Fragment(), View.OnClickListener {
    //The way 2
    var mReplaceFragmentListener: OnReplaceFragmentListener? = null
    //The way 3
    var onReplaceFragmentListener: ((fragment: Fragment) -> Unit) = { _ -> }

    companion object {
        const val ARG_EMAIL = "email"
        const val ARG_PASSWORD = "password"
        fun newInstance(email: String = "", password: String = ""): LoginFragment {
            return LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_EMAIL, email)
                    putString(ARG_PASSWORD, password)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        //Receive data from RegisterFragment and fill in editor
        edtEmailAddress.setText(arguments?.getString(ARG_EMAIL).toString())
        edtPassword.setText(arguments?.getString(ARG_PASSWORD).toString())
        btnLogin.setOnClickListener(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //The way 2
        if (context is OnReplaceFragmentListener) {
            mReplaceFragmentListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        //The way 2
        mReplaceFragmentListener = null
    }

    override fun onClick(p0: View?) {
        if (p0 != null) {
            if (p0.id == R.id.btnLogin) {
//                The way 1:
//                (activity as MainActivity).replaceFragment(EditProfileFragment
//                        .newInstance(email = edtEmailAddress.text.toString()))
//                The way 2:
//                mReplaceFragmentListener?.onReplaceFragmentListener(EditProfileFragment
//                        .newInstance(email = edtEmailAddress.text.toString()))
                //The way 3:
                onReplaceFragmentListener.invoke(EditProfileFragment.newInstance(email = edtEmailAddress.text.toString()))
            }
        }
    }
}
