package asiantech.internship.winter.savedata.db.newtodo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import asiantech.internship.winter.savedata.db.TodoDatabase
import asiantech.internship.winter.savedata.db.todo.Todo
import kotlinx.coroutines.*

class NewTodoViewModel(database: TodoDatabase, application: Application)
    : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val todoDao = database.todoDao

    fun insert(todo: Todo) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                todoDao.insert(todo)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
