package asiantech.internship.summer.savedata

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import asiantech.internship.summer.savedata.DataManager.Companion.USER_ID

class SaveDataActivity : AppCompatActivity() {

    private val SHARED_PREFERENCES_NAME: String = "todolist"
    val sharedPreferences: SharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun getUserIdFromSharedPreferences(): Int {
        return sharedPreferences.getInt(USER_ID, 0)
    }

    fun deleteUserIdFromSharedPreferences(): Boolean {
        val editor = sharedPreferences.edit()
        editor.remove(USER_ID)
        return editor.commit()
    }

    fun addUserIdToSharedPreferences(userId: Int): Boolean {
        val editor = sharedPreferences.edit()
        editor.putInt(USER_ID, userId)
        return editor.commit()
    }
}
