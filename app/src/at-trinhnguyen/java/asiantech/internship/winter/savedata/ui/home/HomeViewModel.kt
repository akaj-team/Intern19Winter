package asiantech.internship.winter.savedata.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import asiantech.internship.winter.savedata.db.TodoDatabase
import asiantech.internship.winter.savedata.db.todo.Todo
import asiantech.internship.winter.savedata.db.user.User
import kotlinx.coroutines.*

class HomeViewModel(private val database: TodoDatabase, application: Application)
    : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var user: User? = null

    private suspend fun update(todo: Todo) {
        withContext(Dispatchers.IO) {
            database.todoDao.updateTodo(todo)
        }
    }

//    private suspend fun getUserById(idUser: Long): User? {
//        return withContext(Dispatchers.IO) {
//            val user = database.userDao.getUserById(idUser)
//            user
//        }
//    }


//    fun getUser(idUser: Long): User? {
//        uiScope.launch {
//            user = getUserById(idUser)
//        }
//        return user
//    }

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

    private val _navigateToEditTodo = MutableLiveData<Long>()
    val navigateToEditTodo: LiveData<Long>
        get() = _navigateToEditTodo

    fun onEditClicked(idTodo: Long) {
        _navigateToEditTodo.value = idTodo
    }

    fun onEditTodoNavigated() {
        _navigateToEditTodo.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
