package asiantech.internship.winter.savedata.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import asiantech.internship.winter.savedata.db.TodoDatabase
import asiantech.internship.winter.savedata.ui.done.DoneViewModel
import asiantech.internship.winter.savedata.ui.login.LoginViewModel
import asiantech.internship.winter.savedata.ui.newtodo.NewTodoViewModel
import asiantech.internship.winter.savedata.ui.signup.SignUpViewModel
import asiantech.internship.winter.savedata.ui.todo.TodoViewModel

class ViewModelFactory(private val dataSource: TodoDatabase, private val application: Application)
    : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
            with(modelClass) {
                when {
                    isAssignableFrom(TodoViewModel::class.java) ->
                        TodoViewModel(dataSource, application)
                    isAssignableFrom(DoneViewModel::class.java) ->
                        DoneViewModel(dataSource, application)
                    isAssignableFrom(LoginViewModel::class.java) ->
                        LoginViewModel(dataSource, application)
                    isAssignableFrom(SignUpViewModel::class.java) ->
                        SignUpViewModel(dataSource, application)
                    isAssignableFrom(NewTodoViewModel::class.java) ->
                        NewTodoViewModel(dataSource, application)
                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class")
                } as T
            }
}
