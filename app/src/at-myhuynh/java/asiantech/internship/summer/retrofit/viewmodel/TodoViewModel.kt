package asiantech.internship.summer.retrofit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import asiantech.internship.summer.retrofit.repository.TodoRepository

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private val todoRepository = TodoRepository.newInstance()

    fun getTodoResponseLiveData() = todoRepository.getAllTodo()

    fun deleteTodo(id: Int) = todoRepository.deleteTodo(id)

}
