package asiantech.internship.winter.layouteditor

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-trinhnguyen`.fragment_sign_up.*


class SignUpFragment : Fragment() {

    private var mCallBack: OnFragmentInteractionListener? = null

    companion object {
        val instance = SignUpFragment()
        const val ARG_NAME = "arg_name"
        const val ARG_EMAIL = "arg_email"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //fragment sign up to fragment login
        tvLoginHere.setOnClickListener {
            fragmentManager?.apply {
                beginTransaction().replace(R.id.flContainer, LoginFragment.instance, null)
                        .addToBackStack(null)
                        .commit()
            }
        }

        btnSignUp.setOnClickListener {
            //send data to fragment login using bundle
            LoginFragment.instance.arguments = Bundle().apply {
                putString(ARG_NAME, edtFullName.text.toString())
                putString(ARG_EMAIL, edtEmail.text.toString())
            }
            //send data to activity using interface
            mCallBack?.onFragmentInteraction("Fragment Sign Up to Activity", 111)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mCallBack = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mCallBack = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(str: String, int: Int)
    }
}
