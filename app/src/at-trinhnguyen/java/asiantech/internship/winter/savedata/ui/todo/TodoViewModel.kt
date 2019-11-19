package asiantech.internship.winter.savedata.ui.todo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import asiantech.internship.winter.savedata.db.TodoDatabase
import asiantech.internship.winter.savedata.db.todo.Todo
import kotlinx.coroutines.*

class TodoViewModel(private val database: TodoDatabase, application: Application)
    : AndroidViewModel(application) {
    private val idUser = 111L
    private val todoDao = database.todoDao
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val todos = todoDao.getTodos(idUser)

    private suspend fun update(todo: Todo) {
        withContext(Dispatchers.IO) {
            todoDao.updateTodo(todo)
        }
    }

    fun insert(todo: Todo) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                todoDao.insert(todo)
            }
        }
    }

    fun deleteTodo(id: Long) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                todoDao.deleteTodoById(id)
            }
        }
    }

    fun updateCompletedById(idTodo: Long) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                todoDao.updateCompletedById(idTodo, true)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
