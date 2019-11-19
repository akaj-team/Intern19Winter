package asiantech.internship.winter.savedata.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import asiantech.internship.winter.savedata.db.TodoDatabase
import asiantech.internship.winter.savedata.db.todo.Todo
import asiantech.internship.winter.savedata.db.user.User
import kotlinx.coroutines.*

class HomeViewModel(private val database: TodoDatabase, application: Application)
    : AndroidViewModel(application) {

    private val idUser = 111L
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    fun insertUser(user: User) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                database.userDao.insert(user)
            }
        }
    }

    private suspend fun update(todo: Todo) {
        withContext(Dispatchers.IO) {
            database.todoDao.updateTodo(todo)
        }
    }

    fun insert(todo: Todo) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                database.todoDao.insert(todo)
            }
        }
    }

    fun deleteTodo(id: Long) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                database.todoDao.deleteTodoById(id)
            }
        }
    }

    fun updateCompletedById(idTodo: Long) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                database.todoDao.updateCompletedById(idTodo, true)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
