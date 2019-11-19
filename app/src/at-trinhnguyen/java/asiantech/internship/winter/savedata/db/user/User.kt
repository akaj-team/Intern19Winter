package asiantech.internship.winter.savedata.db.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "idUser")
        var idUser: Long = 0L,

        @ColumnInfo(name = "username")
        var username: String = "",

        @ColumnInfo(name = "email")
        var email: String = "",

        @ColumnInfo(name = "phoneNumber")
        var phoneNumber: String = "",

        @ColumnInfo(name = "password")
        var password: String = ""

)