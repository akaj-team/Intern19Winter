package asiantech.internship.winter.savedata.ui.signup

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import asiantech.internship.winter.savedata.db.TodoDatabase
import asiantech.internship.winter.savedata.db.user.User
import kotlinx.coroutines.*

class SignUpViewModel(database: TodoDatabase, application: Application)
    : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val userDao = database.userDao

    fun getUsersByEmail(email: String): LiveData<List<User>> {
        return userDao.getUsersByEmail(email)
    }

    fun insertUser(user: User) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                userDao.insert(user)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}