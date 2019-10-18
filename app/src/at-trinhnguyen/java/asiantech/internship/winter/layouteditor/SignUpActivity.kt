package asiantech.internship.winter.layouteditor

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-trinhnguyen`.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        btnSignUp.setOnClickListener {
            intent = Intent(applicationContext, EditProfileActivity::class.java)
            startActivity(intent)
        }
    }
}
