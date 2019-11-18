package asiantech.internship.winter.savedata.ui.todo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import asiantech.internship.winter.savedata.db.todo.Todo
import asiantech.internship.winter.savedata.db.todo.TodoDao
import kotlinx.coroutines.*

class TodoViewModel(private val database: TodoDao, application: Application)
    : AndroidViewModel(application) {
    private val idUser = 111L
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val todos = database.getTodos(idUser)

    private suspend fun update(todo: Todo) {
        withContext(Dispatchers.IO) {
            database.updateTodo(todo)
        }
    }

    fun insert(todo: Todo) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                database.insert(todo)
            }
        }
    }

    fun deleteTodo(id: Long) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                database.deleteTodoById(id)
            }
        }
    }

    fun updateCompletedById(idTodo: Long) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                database.updateCompletedById(idTodo, true)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
