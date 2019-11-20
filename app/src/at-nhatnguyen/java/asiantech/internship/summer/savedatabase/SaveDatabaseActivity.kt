package asiantech.internship.summer.savedatabase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import asiantech.internship.summer.R
import asiantech.internship.summer.savedatabase.fragment.LoginFragment

class SaveDatabaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_database)

        val email =""
        val pass = ""
        supportFragmentManager.beginTransaction().add(R.id.frameLayout, LoginFragment.newInstance(email,pass)).commit()
    }
}
