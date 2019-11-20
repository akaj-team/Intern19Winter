package asiantech.internship.winter.savedata.ui.edittodo

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import asiantech.internship.winter.savedata.db.TodoDatabase

class EditTodoViewModelfactory(
        private val idUser: Long,
        private val dataSource: TodoDatabase,
        private val application: Application)
    : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditTodoViewModel::class.java)) {
            return EditTodoViewModel(idUser, dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}