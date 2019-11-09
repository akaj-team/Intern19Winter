package asiantech.internship.summer.fragment

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-vinhnguyen`.fragment_login.*

class MainActivity : AppCompatActivity(), OnReplaceFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_exercise)
        replaceFragment(RegisterFragment.newInstance())
    }

    fun replaceFragment(fragment: Fragment) {
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(R.id.fmLayout, fragment).addToBackStack(null).commit()
    }

    //The way 2
    override fun onReplaceFragmentListener(fragment: Fragment) {
        replaceFragment(fragment)
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        //The way 3
        (fragment as? LoginFragment).let {
            it?.onReplaceFragmentListener = { fragment ->
                replaceFragment(fragment)
            }
        }
    }
}
