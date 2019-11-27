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
                "CREATE TABLE " + DBContract.UserEntry.TABLE_DATABASE_NAME + " (" +
                        DBContract.UserEntry.COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        DBContract.UserEntry.COLUMN_TABLE_FULL_NAME + " TEXT," +
                        DBContract.UserEntry.COLUMN_TABLE_EMAIL + " TEXT," +
                        DBContract.UserEntry.COLUMN_TABLE_PASSWORD + " TEXT)"
        private const val DROP_USER_TABLE = "DROP TABLE IF EXISTS" + DBContract.UserEntry.TABLE_DATABASE_NAME

        private const val CREATE_TODO_TABLE =
                "CREATE TABLE " + DBContract.UserEntry.TABLE_NAME_TODO + " (" +
                        DBContract.UserEntry.COLUMN_TODO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        DBContract.UserEntry.COLUMN_TODO_NAME + " TEXT," +
                        DBContract.UserEntry.COLUMN_TODO_CONTENT + " TEXT)"
        private const val DROP_TODO_TABLE = "DROP TABLE IF EXISTS" + DBContract.UserEntry.TABLE_NAME_TODO

        private const val CREATE_DONE_TABLE =
                "CREATE TABLE " + DBContract.UserEntry.TABLE_NAME_DONE + " (" +
                        DBContract.UserEntry.COLUMN_DONE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        DBContract.UserEntry.COLUMN_DONE_NAME + " TEXT)"
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
            put(DBContract.UserEntry.COLUMN_TABLE_FULL_NAME, userModel.fullName)
            put(DBContract.UserEntry.COLUMN_TABLE_EMAIL, userModel.email)
            put(DBContract.UserEntry.COLUMN_TABLE_PASSWORD, userModel.password)
        }
        db.insert(DBContract.UserEntry.TABLE_DATABASE_NAME, null, values)
        return true
    }

    fun readUser(): MutableList<UserModel> {
        val users = mutableListOf<UserModel>()
        val db = writableDatabase
        val cursor: Cursor?
        try {
            cursor = db.rawQuery("select * from " + DBContract.UserEntry.TABLE_DATABASE_NAME, null)
        } catch (e: SQLiteException1) {
            db.execSQL(CREATE_USER_TABLE)
            return ArrayList()
        }

        var fullName: String
        var email: String
        var password: String
        if (cursor!!.moveToFirst()) {
            while (!cursor.isAfterLast) {
                fullName = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_TABLE_FULL_NAME))
                email = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_TABLE_EMAIL))
                password = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_TABLE_PASSWORD))

                users.add(UserModel(fullName, email, password))
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
        db.insert(DBContract.UserEntry.TABLE_NAME_TODO, null, values)
        return true
    }

    fun checkUser(email: String, password: String): Boolean {
        val db = readableDatabase
        val column = arrayOf(DBContract.UserEntry.COLUMN_USER_ID)
        val selection = "${DBContract.UserEntry.COLUMN_TABLE_EMAIL}=? AND ${DBContract.UserEntry.COLUMN_TABLE_PASSWORD}=?"
        val selectionArg = arrayOf(email, password)

        val cursor = db.query(DBContract.UserEntry.TABLE_DATABASE_NAME,
                column,
                selection,
                selectionArg,
                null,
                null,
                null)
        val cursorCount = cursor.count
        cursor.close()
        db.close()
        if (cursorCount > 0)
            return true
        return false
    }


    fun readTodo(): MutableList<TodoModel> {
        val users = mutableListOf<TodoModel>()
        val db = writableDatabase
        val cursor: Cursor?
        try {
            cursor = db.rawQuery("select * from " + DBContract.UserEntry.TABLE_NAME_TODO, null)
        } catch (e: SQLiteException1) {
            db.execSQL(CREATE_TODO_TABLE)
            return ArrayList()
        }

        if (cursor!!.moveToFirst()) {
            while (!cursor.isAfterLast) {
                val name = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_TODO_NAME))
                val content = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_TODO_CONTENT))
                users.add(TodoModel(name, content))
                cursor.moveToNext()
            }
        }
        return users
    }

    fun updateTodo(todo: TodoModel) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(DBContract.UserEntry.COLUMN_TODO_NAME, todo.todoName)
        // updating row
        db.update(DBContract.UserEntry.TABLE_NAME_TODO, values, "${DBContract.UserEntry.COLUMN_TODO_NAME} = ?",
                arrayOf(todo.todoName))
        db.close()
    }

    fun deleteTodo(todoModel: TodoModel) {
        val db = writableDatabase
        db.delete(DBContract.UserEntry.TABLE_NAME_TODO, "${DBContract.UserEntry.COLUMN_TODO_NAME} =?",
                arrayOf(todoModel.todoName))
        db.close()
    }
}
