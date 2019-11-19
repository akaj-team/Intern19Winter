package asiantech.internship.summer.viewpager_tablayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R

class ViewPagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        replaceFragment(ViewPagerFragment.newInstance(), false)
    }

    fun replaceFragment(fragment: Fragment, isAddToBackStack: Boolean) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(javaClass.simpleName)
        }
        fragmentTransaction.replace(R.id.flPagerFragment, fragment)
        fragmentTransaction.commit()
    }
}
