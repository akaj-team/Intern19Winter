package asiantech.internship.summer.retrofit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import asiantech.internship.summer.retrofit.model.Todo
import asiantech.internship.summer.retrofit.repository.TodoRepository

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private val todoRepository = TodoRepository.newInstance()

    fun getTodoResponseLiveData() = todoRepository.getAllTodo()

    fun createNewTodo(todo: Todo) = todoRepository.createNewTodo(todo)

    fun updateTodo(todo: Todo) = todoRepository.updateTodo(todo)

    fun deleteTodo(id: Int) = todoRepository.deleteTodo(id)

}
