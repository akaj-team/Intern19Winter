package asiantech.internship.summer.savedata.interfaces

import asiantech.internship.summer.savedata.entity.Todo

interface TodoItemOnclick {

    fun editTodoOnClick(todo: Todo)
    fun deleteTodoOnClick(todo: Todo)
    fun checkBoxTodoOnClick(todo: Todo)
}
