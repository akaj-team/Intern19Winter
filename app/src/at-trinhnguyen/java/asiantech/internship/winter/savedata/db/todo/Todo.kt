package asiantech.internship.winter.savedata.db.todo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class Todo(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "idTodo")
        var idTodo: Long = 0L,

        @ColumnInfo(name = "idUser")
        var idUser: Long = 0L,

        @ColumnInfo(name = "title")
        var title: String = "",

        @ColumnInfo(name = "description")
        var description: String = "",

        @ColumnInfo(name = "completed")
        var isCompleted: Boolean = false
) {
    val isActive
        get() = !isCompleted

    val isEmpty
        get() = title.isEmpty() || description.isEmpty()
}
