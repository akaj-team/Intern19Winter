package asiantech.internship.summer.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-myhuynh`.fragment_sign_up.*
import kotlinx.android.synthetic.`at-myhuynh`.fragment_sign_up.edtEmail

class SignUpFragment : Fragment() {

    var onClickListener: ((fragment: Fragment, isAddToBackTack: Boolean) -> Unit) = { _, _ -> }
    private var mListener: OnReplaceFragmentListener? = null

    companion object {
        private val instance = SignUpFragment()
        fun newInstance(): SignUpFragment {
            return instance
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** I */
        btnSignUp.setOnClickListener {
            (activity as? MainActivity)
                    ?.replaceFragment(LoginFragment.newInstance(
                            edtFullName.text.toString(), edtEmail.text.toString()), true)
        }

        tvLoginHere.setOnClickListener {
            (activity as? MainActivity)
                    ?.replaceFragment(LoginFragment.newInstance(), false)
        }

        /** II */
        /*btnSignUp.setOnClickListener {
            onClickListener.invoke(LoginFragment.newInstance(edtFullName.text.toString(), edtEmail.text.toString()), true)
        }

        tvLoginHere.setOnClickListener {
            onClickListener.invoke(LoginFragment.newInstance(), false)
        }*/

        /** III */
        /*btnSignUp.setOnClickListener {
            mListener?.onFragmentInteraction(
                    LoginFragment.newInstance(edtFullName.text.toString(), edtEmail.text.toString()), true)
        }

        tvLoginHere.setOnClickListener {
            mListener?.onFragmentInteraction(LoginFragment.newInstance(), false)
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
