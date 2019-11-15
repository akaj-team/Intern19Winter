package asiantech.internship.winter.savedata.db.todo

data class Todo(
        var idTodo: Long = 0L,

        var idUser: Long = 0L,

        var title: String = "",

        var description: String = "",

        var isCompleted: Boolean = false
)