package asiantech.internship.summer.savedatabase.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.util.ArrayList


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
                        DBContract.UserEntry.COLUMN_TODO_NAME + " TEXT)"
        private const val DROP_TODO_TABLE = "DROP TABLE IF EXISTS" + DBContract.UserEntry.TABLE_NAME_TODO
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_USER_TABLE)
        db?.execSQL(CREATE_TODO_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DROP_USER_TABLE)
        db?.execSQL(DROP_TODO_TABLE)
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

    fun insertTodo(todoModel: TodoModel):Boolean{
        val db = writableDatabase
        val values = ContentValues()
        values.put(DBContract.UserEntry.COLUMN_TODO_NAME,todoModel.todoName)
        db.insert(DBContract.UserEntry.TABLE_NAME_TODO,null,values)
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
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBContract.UserEntry.TABLE_NAME_TODO, null)
        } catch (e: SQLiteException) {
            db.execSQL(CREATE_TODO_TABLE)
            return ArrayList()
        }


        var name: String
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                //userid = cursor.getInt(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_USER_ID))
                name = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_TODO_NAME))

                users.add(TodoModel( name))
                cursor.moveToNext()
            }
        }
        Log.d("xxx", "${users.size}")
        return users
    }
}
