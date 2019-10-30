package asiantech.internship.summer.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-myhuynh`.activity_edit_profile.edtEmail
import kotlinx.android.synthetic.`at-myhuynh`.activity_edit_profile.edtFullName

class EditProfileFragment : Fragment() {

    private var mFullName: String? = null
    private var mEmail: String? = null

    companion object {
        fun newInstance(fullName: String?, email: String) =
                EditProfileFragment().apply {
                    arguments = Bundle().apply {
                        putString(LoginFragment.ARG_USER_NAME, fullName)
                        putString(LoginFragment.ARG_EMAIL, email)
                    }

                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mFullName = it.getString(LoginFragment.ARG_USER_NAME)
            mEmail = it.getString(LoginFragment.ARG_EMAIL)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        edtFullName.setText(mFullName)
        edtEmail.setText(mEmail)
    }
}
