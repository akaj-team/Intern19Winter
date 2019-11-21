package asiantech.internship.winter.savedata.ui.editprofile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import asiantech.internship.winter.savedata.db.TodoDatabase
import asiantech.internship.winter.savedata.db.user.User
import kotlinx.coroutines.*

class EditProfileViewModel(idUser: Long = 0L, database: TodoDatabase, application: Application)
    : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val userDao = database.userDao
    private val user: LiveData<User>

    fun getUser() = user

    init {
        user = userDao.getUserById(idUser)
    }

    fun update(user: User) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                userDao.update(user)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
