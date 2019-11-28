package asiantech.internship.winter.savedata.ui.edittodo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import asiantech.internship.winter.savedata.db.TodoDatabase
import asiantech.internship.winter.savedata.db.todo.Todo
import kotlinx.coroutines.*

class EditTodoViewModel(private val idTodo: Long = 0L, database: TodoDatabase, application: Application)
    : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val todoDao = database.todoDao
    private val todo: LiveData<Todo>

    fun getTodo() = todo

    init {
        todo = todoDao.getTodoById(idTodo)
    }

    fun update(todo: Todo) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                todoDao.updateTodo(todo)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
