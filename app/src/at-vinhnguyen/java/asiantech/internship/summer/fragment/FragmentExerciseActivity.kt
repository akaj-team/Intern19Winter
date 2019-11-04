package asiantech.internship.summer.fragment

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import asiantech.internship.summer.R

class FragmentExerciseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_exercise)
        initView()
    }

    private fun initView() {
        supportFragmentManager.beginTransaction().add(R.id.fmLayout, LoginFragment.newInstance()).commit()
        val mBtnLogin = findViewById<Button>(R.id.btnLogin)
        mBtnLogin.setOnClickListener {}
    }

    private fun callEditProfileFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.fmLayout, EditProfileFragment.newInstance()).commit()
    }
}