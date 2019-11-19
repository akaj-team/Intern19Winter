package asiantech.internship.winter.savedata.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import asiantech.internship.winter.savedata.db.TodoDatabase
import asiantech.internship.winter.savedata.db.user.User
import kotlinx.coroutines.*

class LoginViewModel(private val database: TodoDatabase, application: Application)
    : AndroidViewModel(application) {
    private val idUser = 111L
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val userDao = database.userDao


    fun login(email: String, password: String): LiveData<List<User>> {
        return userDao.login(email, password)
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