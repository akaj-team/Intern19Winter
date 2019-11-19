package asiantech.internship.winter.savedata.ui.done

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import asiantech.internship.winter.savedata.db.TodoDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class DoneViewModel(private val database: TodoDatabase, application: Application)
    : AndroidViewModel(application) {
    private val idUser = 111L
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val todos = database.todoDao.getDones(idUser)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
