package asiantech.internship.summer.exercise
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R


class MainSignActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportFragmentManager.beginTransaction().add(R.id.flFragment, SignFragment.newInstance())
                .addToBackStack(null)
                .commit()
    }
    fun replaceFragment(fragment: Fragment) {
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(R.id.flFragment, fragment).addToBackStack(null).commit()
    }
}