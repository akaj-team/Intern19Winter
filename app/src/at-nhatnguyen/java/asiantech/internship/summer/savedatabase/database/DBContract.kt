package asiantech.internship.summer.savedatabase.database

import android.provider.BaseColumns

object DBContract {
    class UserEntry : BaseColumns {
        companion object {
            const val TABLE_DATABASE_NAME = "listuser"
            const val COLUMN_USER_ID = "userid"
            const val COLUMN_TABLE_FULL_NAME = "fullname"
            const val COLUMN_TABLE_EMAIL = "email"
            const val COLUMN_TABLE_PASSWORD = "password"

            const val COLUMN_TODO_ID = "id"
            const val TABLE_NAME_TODO = "todo"
            const val COLUMN_TODO_NAME = "todoname"

        }
    }
}