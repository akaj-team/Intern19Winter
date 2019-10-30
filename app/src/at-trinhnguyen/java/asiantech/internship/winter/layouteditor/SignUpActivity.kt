package asiantech.internship.winter.layouteditor

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import asiantech.internship.summer.R

class SignUpActivity : AppCompatActivity(), SignUpFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        supportFragmentManager.beginTransaction()
                .add(R.id.flContainer, SignUpFragment.instance, null)
                .addToBackStack(null)
                .commit()

        val viewModel = ViewModelProviders.of(this)[SharedViewModel::class.java]
        viewModel.bundle.observe(this, Observer { bundle ->
            bundle.apply {
                getString(EditProfileFragment.ARG_EMAIL)
                        .also { Log.d("xxx", "From Fragment Edit Profile email: $it") }
                getString(EditProfileFragment.ARG_PHONE)
                        .also { Log.d("xxx", "From Fragment Edit Profile phone: $it") }
            }
        })
    }

    override fun onFragmentInteraction(str: String, int: Int) {
        Log.d("xxx", "From Fragment Sign Up | $str $int")
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)

        if (fragment is LoginFragment) {
            LoginFragment.instance.callBack = { str, int ->
                Log.d("xxx", "From Fragment Login | $str $int")
            }
        }
    }
}
