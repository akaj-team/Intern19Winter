package asiantech.internship.winter.savedata

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import asiantech.internship.summer.R


class SaveDataActivity : AppCompatActivity() {
    private var isLogin = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_data)

        getPreferences(Context.MODE_PRIVATE)?.apply {
            isLogin = getBoolean(getString(R.string.pref_is_login), false)
        }

        if (isLogin) {
            this.findNavController(R.id.saveDataNavHostFragment).navigate(R.id.action_loginFragment_to_homeFragment)
        }

    }
}
