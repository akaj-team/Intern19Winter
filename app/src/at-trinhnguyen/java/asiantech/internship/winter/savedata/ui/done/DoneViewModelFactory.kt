package asiantech.internship.winter.savedata.ui.done

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import asiantech.internship.winter.savedata.db.todo.TodoDao

class DoneViewModelFactory(private val dataSource: TodoDao, private val application: Application)
    : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DoneViewModel::class.java)) {
            return DoneViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
