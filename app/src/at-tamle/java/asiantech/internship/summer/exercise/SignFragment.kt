package asiantech.internship.summer.exercise


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-tamle`.fragment_profile.edtEmail
import kotlinx.android.synthetic.`at-tamle`.fragment_profile.edtFullName
import kotlinx.android.synthetic.`at-tamle`.fragment_sign.*


class SignFragment : Fragment() {

    companion object {
        fun newInstance() = SignFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sign, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvHere.setOnClickListener {
            (activity as? MainSignActivity)
                    ?.replaceFragment(LoginFragment.newInstance())
        }


        btnSignUp.setOnClickListener {
            (activity as? MainSignActivity)

                    ?.replaceFragment(LoginFragment.newInstance(
                            edtFullName.text.toString(), edtEmail.text.toString()
                    )
                    )
        }
    }
}