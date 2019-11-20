package asiantech.internship.winter.savedata.ui.done

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import asiantech.internship.winter.savedata.db.TodoDatabase
import asiantech.internship.winter.savedata.db.todo.Todo

class DoneViewModel(database: TodoDatabase, application: Application)
    : AndroidViewModel(application) {

    private val todoDao = database.todoDao

    fun getDonesByIdUser(idUser: Long): LiveData<List<Todo>> {
        return todoDao.getDones(idUser)
    }
}
