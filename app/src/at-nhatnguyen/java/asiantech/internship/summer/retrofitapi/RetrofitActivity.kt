package asiantech.internship.summer.retrofitapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import asiantech.internship.summer.R
import asiantech.internship.summer.retrofitapi.fragment.ToDoFragment

class RetrofitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)
        supportFragmentManager.beginTransaction().add(R.id.frActivity, ToDoFragment.newInstance()).commit()
    }
}
