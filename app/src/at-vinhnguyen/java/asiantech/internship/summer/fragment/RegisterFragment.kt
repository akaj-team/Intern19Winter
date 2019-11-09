package asiantech.internship.summer.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-vinhnguyen`.fragment_register.*

class RegisterFragment : Fragment(), View.OnClickListener {

    companion object {
        private const val ARG_FULLNAME = "fullname"
        private const val ARG_EMAIL = "email"
        fun newInstance(fullName: String = "", email: String = "") = RegisterFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_FULLNAME, fullName)
                putString(ARG_EMAIL, email)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        //Receive data from EditProfileFragment and fill in editor
        edtFullName.setText(arguments?.getString(ARG_FULLNAME))
        edtEmailAddress.setText(arguments?.getString(ARG_EMAIL))
        btnCreateAccount.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnCreateAccount -> (activity as? MainActivity)?.replaceFragment(
                    LoginFragment.newInstance(edtEmailAddress.text.toString(), edtPassword.text.toString()))
        }
    }
}
