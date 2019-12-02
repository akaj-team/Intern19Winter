package asiantech.internship.summer.savedatabase.database

import android.provider.BaseColumns

object DBContract {
    class UserEntry : BaseColumns {
        companion object {
            const val TABLE_NAME_USERS = "listuser"
            const val COLUMN_USER_ID = "userid"
            const val COLUMN_USER_FULL_NAME = "fullname"
            const val COLUMN_USER_EMAIL = "email"
            const val COLUMN_USER_PASSWORD = "password"

            const val COLUMN_TODO_ID = "id"
            const val COLUMN_TODO_NAME = "todoname"
            const val COLUMN_TODO_CONTENT = "content"
            const val TABLE_NAME_TODO = "todo"

            const val COLUMN_DONE_ID = "id"
            const val COLUMN_DONE_NAME = "donename"
            const val TABLE_NAME_DONE = "done"
        }
    }
}
