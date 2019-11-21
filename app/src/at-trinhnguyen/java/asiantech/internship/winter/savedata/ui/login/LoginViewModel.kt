package asiantech.internship.winter.savedata.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import asiantech.internship.winter.savedata.db.TodoDatabase
import asiantech.internship.winter.savedata.db.user.User

class LoginViewModel(database: TodoDatabase, application: Application)
    : AndroidViewModel(application) {
    private val userDao = database.userDao

    fun login(email: String, password: String): LiveData<List<User>> {
        return userDao.login(email, password)
    }
}
