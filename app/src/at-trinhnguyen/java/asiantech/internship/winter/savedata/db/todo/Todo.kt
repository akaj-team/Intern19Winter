package asiantech.internship.winter.savedata.db.todo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import asiantech.internship.winter.savedata.db.user.User

@Entity(tableName = "todo_table",
        foreignKeys = [ForeignKey(entity = User::class,
                parentColumns = ["idUser"],
                childColumns = ["idUser"],
                onDelete = ForeignKey.NO_ACTION)])
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
)
