package asiantech.internship.winter.savedata.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import asiantech.internship.winter.savedata.db.todo.Todo
import asiantech.internship.winter.savedata.db.todo.TodoDao
import asiantech.internship.winter.savedata.db.user.User
import asiantech.internship.winter.savedata.db.user.UserDao

@Database(entities = [Todo::class, User::class], version = 1, exportSchema = true)
abstract class TodoDatabase : RoomDatabase() {

    abstract val todoDao: TodoDao
    abstract val userDao: UserDao

    companion object {
        @Volatile
        private var INSTANCE: TodoDatabase? = null

        fun getInstance(context: Context): TodoDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        TodoDatabase::class.java,
                        "todo_database"
                )
                        .fallbackToDestructiveMigration()
                        .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
