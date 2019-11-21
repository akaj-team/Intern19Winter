package asiantech.internship.summer.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R

class MainActivity : AppCompatActivity(), OnReplaceFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        replaceFragment(SignUpFragment.newInstance(), false)
    }

    internal fun replaceFragment(fragment: Fragment, isAddToBackTack: Boolean) {
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(R.id.flFragment, fragment)
        if (isAddToBackTack) {
            beginTransaction.addToBackStack(fragment.javaClass.simpleName)
        }
        beginTransaction.commit()
    }

    /** II */
    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        (fragment as? SignUpFragment).let {
            it?.onClickListener = { fragment, isAddToBackTack ->
                replaceFragment(fragment, isAddToBackTack)
            }
        }

        (fragment as? LoginFragment).let {
            it?.onClickListener = { fragment, isAddToBackTack ->
                replaceFragment(fragment, isAddToBackTack)
            }
        }
    }

    /** III */
    override fun onFragmentInteraction(fragment: Fragment, isAddToBackTack: Boolean) {
        replaceFragment(fragment, isAddToBackTack)
    }
}
