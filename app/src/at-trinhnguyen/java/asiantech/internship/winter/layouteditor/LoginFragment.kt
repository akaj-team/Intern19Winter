package asiantech.internship.winter.layouteditor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-trinhnguyen`.fragment_login.*

class LoginFragment : Fragment() {

    private var mName: String? = null
    private var mEmail: String? = null
    var callBack: ((String, Int) -> Unit)? = null

    companion object {
        private const val ARG_NAME = "arg_name"
        private const val ARG_EMAIL = "arg_email"
        private val instance = LoginFragment()
        @JvmStatic
        fun newInstance(name: String, email: String) =
                instance.apply {
                    arguments = Bundle().apply {
                        putString(ARG_NAME, name)
                        putString(ARG_EMAIL, email)
                    }
                }

        fun getInstance() = instance
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //get data from fragment sign up
        arguments?.let {
            mName = it.getString(ARG_NAME)
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
        //fragment login to fragment edit profile
        tvCreateAccountHere.setOnClickListener {
            fragmentManager?.apply {
                beginTransaction().replace(R.id.flContainer, EditProfileFragment.newInstance(), null)
                        .addToBackStack(null)
                        .commit()
            }
        }
        btnLogin.setOnClickListener {
            //send data to activity using lambda
            callBack?.invoke("Fragment Login to Activity", 222)
        }
    }
}
