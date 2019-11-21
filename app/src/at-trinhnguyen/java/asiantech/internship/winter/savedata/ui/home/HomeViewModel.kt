package asiantech.internship.winter.savedata.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import asiantech.internship.winter.savedata.db.TodoDatabase
import asiantech.internship.winter.savedata.db.todo.Todo
import asiantech.internship.winter.savedata.db.user.User
import kotlinx.coroutines.*

class HomeViewModel(idUser: Long, private val database: TodoDatabase, application: Application)
    : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val user: LiveData<User> = database.userDao.getUserById(idUser)
    fun getUser() = user

    fun insert(todo: Todo) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                database.todoDao.insert(todo)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
