package asiantech.internship.summer.savedatabase.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.*
import android.database.sqlite.SQLiteException as SQLiteException1

class DBHandling(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "ListUser.db"

        private const val CREATE_USER_TABLE =
                "CREATE TABLE " + DBContract.UserEntry.TABLE_NAME_USERS + " (" +
                        DBContract.UserEntry.COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        DBContract.UserEntry.COLUMN_USER_FULL_NAME + " TEXT," +
                        DBContract.UserEntry.COLUMN_USER_EMAIL + " TEXT," +
                        DBContract.UserEntry.COLUMN_USER_PASSWORD + " TEXT)"
        private const val DROP_USER_TABLE = "DROP TABLE IF EXISTS" + DBContract.UserEntry.TABLE_NAME_USERS

        private const val CREATE_TODO_TABLE =
                "CREATE TABLE " + DBContract.UserEntry.TABLE_NAME_TODO + " (" +
                        DBContract.UserEntry.COLUMN_TODO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        DBContract.UserEntry.COLUMN_TODO_NAME + " TEXT," +
                        DBContract.UserEntry.COLUMN_TODO_CONTENT + " TEXT," +
                        DBContract.UserEntry.COLUMN_USER_ID + " INTEGER," +
                        " FOREIGN KEY" + "(" + DBContract.UserEntry.COLUMN_USER_ID + ")" +
                        " REFERENCES " + DBContract.UserEntry.TABLE_NAME_USERS +
                        " (" + DBContract.UserEntry.COLUMN_USER_ID + "))"
        private const val DROP_TODO_TABLE = "DROP TABLE IF EXISTS" + DBContract.UserEntry.TABLE_NAME_TODO

        private const val CREATE_DONE_TABLE =
                "CREATE TABLE " + DBContract.UserEntry.TABLE_NAME_DONE + " (" +
                        DBContract.UserEntry.COLUMN_DONE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        DBContract.UserEntry.COLUMN_DONE_NAME + " TEXT," +
                        DBContract.UserEntry.COLUMN_TODO_CONTENT + " TEXT," +
                        DBContract.UserEntry.COLUMN_USER_ID + " INTEGER," +
                        " FOREIGN KEY" + " (" + DBContract.UserEntry.COLUMN_USER_ID + ")" +
                        " REFERENCES " + DBContract.UserEntry.TABLE_NAME_USERS +
                        " (" + DBContract.UserEntry.COLUMN_USER_ID + "))"
        private const val DROP_DONE_TABLE = "DROP TABLE IF EXISTS" + DBContract.UserEntry.TABLE_NAME_DONE
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_USER_TABLE)
        db?.execSQL(CREATE_TODO_TABLE)
        db?.execSQL(CREATE_DONE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DROP_USER_TABLE)
        db?.execSQL(DROP_TODO_TABLE)
        db?.execSQL(DROP_DONE_TABLE)
        onCreate(db)
    }

    fun insertUser(userModel: UserModel): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(DBContract.UserEntry.COLUMN_USER_FULL_NAME, userModel.fullName)
            put(DBContract.UserEntry.COLUMN_USER_EMAIL, userModel.email)
            put(DBContract.UserEntry.COLUMN_USER_PASSWORD, userModel.password)
        }
        db.insert(DBContract.UserEntry.TABLE_NAME_USERS, null, values)
        return true
    }

    fun checkLogin(email: String, password: String): MutableList<UserModel> {
        val users = mutableListOf<UserModel>()
        val db = writableDatabase
        val cursor: Cursor?
        try {
            cursor = db.rawQuery("select * from ${DBContract.UserEntry.TABLE_NAME_USERS} where ${DBContract.UserEntry.COLUMN_USER_EMAIL} = ? " +
                    "and ${DBContract.UserEntry.COLUMN_USER_PASSWORD} = ? ", arrayOf(email, password))
        } catch (e: SQLiteException1) {
            db.execSQL(CREATE_USER_TABLE)
            return ArrayList()
        }
        if (cursor!!.moveToFirst()) {
            while (!cursor.isAfterLast) {
                val idUser = cursor.getInt(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_USER_ID))
                val fullName = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_USER_FULL_NAME))
                val emailLogin = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_USER_EMAIL))
                val pass = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_USER_PASSWORD))

                users.add(UserModel(idUser, fullName, emailLogin, pass))
                cursor.moveToNext()
            }
        }
        return users
    }

    fun insertTodo(todoModel: TodoModel): Boolean {
        val db = writableDatabase
        val values = ContentValues()
        values.put(DBContract.UserEntry.COLUMN_TODO_NAME, todoModel.todoName)
        values.put(DBContract.UserEntry.COLUMN_TODO_CONTENT, todoModel.todoContent)
        values.put(DBContract.UserEntry.COLUMN_USER_ID, todoModel.userId)
        db.insert(DBContract.UserEntry.TABLE_NAME_TODO, null, values)
        return true
    }

    fun readTodo(userId: Int): MutableList<TodoModel> {
        val users = mutableListOf<TodoModel>()
        val db = writableDatabase
        val cursor: Cursor?
        try {
            cursor = db.rawQuery("select * from " + DBContract.UserEntry.TABLE_NAME_TODO + " WHERE " + DBContract.UserEntry.COLUMN_USER_ID + " = ?", arrayOf(userId.toString()))
        } catch (e: SQLiteException1) {
            db.execSQL(CREATE_TODO_TABLE)
            return ArrayList()
        }

        if (cursor!!.moveToFirst()) {
            while (!cursor.isAfterLast) {
                val content = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_TODO_CONTENT))
                val name = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_TODO_NAME))
                val id = cursor.getInt(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_USER_ID))
                val todoId = cursor.getInt(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_TODO_ID))
                users.add(TodoModel(name, content, id, todoId))
                cursor.moveToNext()
            }
        }
        return users
    }


    fun updateTodo(todo: TodoModel) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(DBContract.UserEntry.COLUMN_TODO_NAME, todo.todoName)
        values.put(DBContract.UserEntry.COLUMN_TODO_CONTENT, todo.todoContent)
        db.update(DBContract.UserEntry.TABLE_NAME_TODO, values, "${DBContract.UserEntry.COLUMN_TODO_ID} = ?",
                arrayOf(todo.todoId.toString()))
    }

    fun deleteTodo(todoModel: TodoModel) {
        val db = writableDatabase
        db.delete(DBContract.UserEntry.TABLE_NAME_TODO, "${DBContract.UserEntry.COLUMN_TODO_NAME} =?",
                arrayOf(todoModel.todoName))
        db.close()
    }
}
