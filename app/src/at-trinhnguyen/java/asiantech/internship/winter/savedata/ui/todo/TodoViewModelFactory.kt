package asiantech.internship.winter.savedata.ui.todo

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import asiantech.internship.winter.savedata.db.todo.TodoDao

class TodoViewModelFactory(private val dataSource: TodoDao, private val application: Application)
    : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            return TodoViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
