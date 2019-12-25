package asiantech.internship.summer.retrofit.interfaces

import asiantech.internship.summer.retrofit.model.Todo

interface TodoItemOnclick {
    fun editTodoOnClick(todo: Todo)
    fun deleteTodoOnClick(todo: Todo)
    fun checkBoxTodoOnClick(todo: Todo)
}
