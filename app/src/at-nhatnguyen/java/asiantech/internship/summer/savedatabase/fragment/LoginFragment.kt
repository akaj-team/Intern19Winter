package asiantech.internship.summer.savedatabase.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import asiantech.internship.summer.savedatabase.database.DBHandling
import kotlinx.android.synthetic.`at-nhatnguyen`.fragment_login.*

class LoginFragment : Fragment() {

    companion object {
        private const val ARG_PARAM_1 = "email"
        private const val ARG_PARAM_2 = "pass"
        fun newInstance(email:String, password:String) = LoginFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM_1,email)
                putString(ARG_PARAM_2,password)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edtEmail.setText(arguments?.getString(ARG_PARAM_1))
        edtPassword.setText(arguments?.getString(ARG_PARAM_2))

        btnLogin.setOnClickListener {
            val userHandling = context?.let { it1 -> DBHandling(it1) }
            val email = edtEmail.text.toString()
            val pass = edtPassword.text.toString()
            if (userHandling!!.checkUser(email = email,password = pass)){
            fragmentManager?.beginTransaction()?.
                    replace(R.id.frameLayout, ViewPagerFragment.newInstance())?.
                    commit()
        }else{
                Toast.makeText(activity,"Email or password incorrect",Toast.LENGTH_SHORT).show()
            }
        }

        tvCreateAccount.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.frameLayout, RegisterFragment.newInstance())?.addToBackStack(null)?.commit()
        }
    }
}
