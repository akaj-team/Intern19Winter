package asiantech.internship.winter.savedata.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import asiantech.internship.winter.savedata.db.todo.TodoDao

class HomeViewModel(private val database: TodoDao, application: Application)
    : AndroidViewModel(application) {

}
