package asiantech.internship.summer.savedata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-tamle`.fragment_tablayout.*

class LoginRegisterActivity : AppCompatActivity() {
    private val mFragments = mutableListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_tablayout)
        initView()
    }

    private fun initView() {
        mFragments.add(LoginFragment())
        mFragments.add(RegisterFragment())

        viewPager.adapter = ViewPagerAdapter(
            supportFragmentManager,
            mFragments,
            listOf(R.string.login, R.string.register)
        )
        tablayout.setupWithViewPager(viewPager)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mFragments.get(1).onActivityResult(requestCode, resultCode, data)
    }
}
