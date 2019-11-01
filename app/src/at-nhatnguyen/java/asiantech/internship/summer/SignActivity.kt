package asiantech.internship.summer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import asiantech.internship.summer.fragment.SignUpFragment

class SignActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)
        supportFragmentManager.beginTransaction().add(R.id.flSign, SignUpFragment.newInstance()).commit()
    }
}
