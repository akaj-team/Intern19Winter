package asiantech.internship.summer.retrofitapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import asiantech.internship.summer.R

class RetrofitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)
        supportFragmentManager.beginTransaction().add(R.id.frActivity,ToDoFragment.newInstance()).commit()
    }
}
