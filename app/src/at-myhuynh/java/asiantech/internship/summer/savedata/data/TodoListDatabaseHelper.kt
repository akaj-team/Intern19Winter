package asiantech.internship.summer.savedata.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import asiantech.internship.summer.savedata.entity.Todo
import asiantech.internship.summer.savedata.entity.User

class TodoListDatabaseHelper(context: Context) :
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "todoList"
        private const val DATABASE_VERSION = 2

        private const val TABLE_TODO = "todo"
        private const val TABLE_USER = "user"

        private const val KEY_TODO_ID = "id"
        private const val KEY_USER_ID_PK = "userId"
        private const val KEY_TODO_CONTENT = "content"
        private const val KEY_TODO_STATUS = "status"

        private const val KEY_USER_ID = "id"
        private const val KEY_USER_NAME = "name"
        private const val KEY_USER_PASSWORD = "password"
        private const val KEY_USER_NICKNAME = "nickname"
        private const val KEY_USER_PATH = "path"

        private const val CREATE_TABLE_TODO = "CREATE TABLE $TABLE_TODO ($KEY_TODO_ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "$KEY_USER_ID_PK INTEGER REFERENCES $TABLE_USER," +
                "$KEY_TODO_CONTENT TEXT," +
                "$KEY_TODO_STATUS INTEGER)"

        private const val CREATE_TABLE_USER = "CREATE TABLE $TABLE_USER ($KEY_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "$KEY_USER_NAME TEXT, " +
                "$KEY_USER_PASSWORD TEXT, " +
                "$KEY_USER_NICKNAME TEXT, " +
                "$KEY_USER_PATH TEXT)"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_TODO)
        db?.execSQL(CREATE_TABLE_USER)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion != newVersion) {
            db?.execSQL("DROP TABLE IF EXISTS $TABLE_TODO")
            db?.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        }
        onCreate(db)
    }

    fun insertUser(user: User) {
        val db = writableDatabase
        val value = ContentValues()
        value.put(KEY_USER_NAME, user.userName)
        value.put(KEY_USER_PASSWORD, user.password)
        value.put(KEY_USER_NICKNAME, user.nickname)
        value.put(KEY_USER_PATH, user.avatar)
        db.insertOrThrow(TABLE_USER, null, value)
    }

    fun readUserLogin(userName: String, password: String): MutableList<User> {
        val users = mutableListOf<User>()
        val db = writableDatabase
        val cursor: Cursor?

        try {
            cursor = db.rawQuery(
                    "SELECT * FROM $TABLE_USER WHERE $KEY_USER_NAME = ? AND $KEY_USER_PASSWORD = ?",
                    arrayOf(userName, password))
        } catch (e: SQLException) {
            return users
        }

        if (cursor!!.moveToFirst()) {
            while (!cursor.isAfterLast) {
                val id = cursor.getInt(cursor.getColumnIndex(KEY_USER_ID))
                val userName = cursor.getString(cursor.getColumnIndex(KEY_USER_NAME))
                val password = cursor.getString(cursor.getColumnIndex(KEY_USER_PASSWORD))
                val nickName = cursor.getString(cursor.getColumnIndex(KEY_USER_NICKNAME))
//                val path = cursor.getString(cursor.getColumnIndex(KEY_USER_PATH))

                users.add(User(id, userName, password, nickName, 0))
                cursor.moveToNext()
            }
        }
        return users
    }

    fun readUsers(): MutableList<User> {
        val users = mutableListOf<User>()
        val db = writableDatabase
        var cursor: Cursor?

        try {
            cursor = db.rawQuery("SELECT * FROM $TABLE_USER", null)
        } catch (e: SQLException) {
            db.execSQL(CREATE_TABLE_USER)
            return users
        }

        if (cursor!!.moveToFirst()) {
            while (!cursor.isAfterLast) {
                val id = cursor.getInt(cursor.getColumnIndex(KEY_USER_ID))
                val userName = cursor.getString(cursor.getColumnIndex(KEY_USER_NAME))
                val password = cursor.getString(cursor.getColumnIndex(KEY_USER_PASSWORD))
                val nickName = cursor.getString(cursor.getColumnIndex(KEY_USER_NICKNAME))
//                val path = cursor.getString(cursor.getColumnIndex(KEY_USER_PATH))

                users.add(User(id, userName, password, nickName, 0))
                cursor.moveToNext()
            }
        }
        return users
    }

    fun insertTodo(todo: Todo) {
        val db = writableDatabase
        val value = ContentValues()
        value.put(KEY_TODO_CONTENT, todo.title)
        value.put(KEY_USER_ID_PK, todo.idUser)
        value.put(KEY_TODO_STATUS, todo.status)
        db.insertOrThrow(TABLE_TODO, null, value)
    }

    fun readTodos(): MutableList<Todo> {
        val todoList = mutableListOf<Todo>()
        val db = writableDatabase
        var cursor: Cursor?

        try {
            cursor = db.rawQuery("SELECT * FROM $TABLE_TODO", null)
        } catch (e: SQLException) {
            db.execSQL(CREATE_TABLE_USER)
            return todoList
        }

        if (cursor!!.moveToFirst()) {
            while (!cursor.isAfterLast) {
                val id = cursor.getInt(cursor.getColumnIndex(KEY_TODO_ID))
                val idUser = cursor.getInt(cursor.getColumnIndex(KEY_USER_ID_PK))
                val title = cursor.getString(cursor.getColumnIndex(KEY_TODO_CONTENT))
                val status = cursor.getInt(cursor.getColumnIndex(KEY_TODO_STATUS))

                todoList.add(Todo(id, idUser, title, status))
                cursor.moveToNext()
            }
        }
        return todoList
    }

    fun updateTodo(todo: Todo): Int {
        val db = writableDatabase
        val value = ContentValues()
        value.put(KEY_TODO_CONTENT, todo.title)
        value.put(KEY_TODO_STATUS, todo.status)
        return db.update(TABLE_TODO, value, "$KEY_TODO_ID = ?", arrayOf(todo.id.toString()))
    }

    fun deleteTodo(todo: Todo): Int {
        val db = writableDatabase
        return db.delete(TABLE_TODO, "$KEY_TODO_ID = ?", arrayOf(todo.id.toString()))
    }
}
