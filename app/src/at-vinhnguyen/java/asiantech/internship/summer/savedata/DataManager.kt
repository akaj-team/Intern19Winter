package asiantech.internship.summer.savedata

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import asiantech.internship.summer.savedata.model.Note
import asiantech.internship.summer.savedata.model.User

class DataManager private constructor(var context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) :
        SQLiteOpenHelper(context, name, factory, version) {
    companion object {
        const val DATABASE_NAME = "TodoListDB"
        const val TABLE_NAME_USER = "Users"
        const val TABLE_NAME_NOTES = "Notes"
        const val USER_ID = "UserId"
        const val USER_NAME = "UserName"
        const val USER_PASSWORD = "Password"
        const val USER_IMG_PATH = "ImagePath"
        const val NOTE_ID = "NoteId"
        const val NOTE_TODO = "Todo"
        const val NOTE_STATUS = "Status"
        const val CREATE_NOTES_TABLE = "CREATE TABLE " + TABLE_NAME_NOTES + " ( " +
                NOTE_ID + " INTEGER PRIMARY KEY " +
                USER_ID + " INTEGER, " +
                NOTE_TODO + " TEXT, " +
                NOTE_STATUS + " INTEGER)"
        const val CREATE_USER_TABLE = "CREATE TABLE " + TABLE_NAME_USER + " ( " +
                USER_ID + " INTEGER PRIMARY KEY, " +
                USER_NAME + " TEXT, " +
                USER_PASSWORD + " TEXT, " +
                USER_IMG_PATH + " TEXT)"
        const val DROP_USERS_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME_USER"
        const val DROP_NOTES_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME_NOTES"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(CREATE_USER_TABLE)
        p0?.execSQL(CREATE_NOTES_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL(DROP_USERS_TABLE)
        p0?.execSQL(DROP_NOTES_TABLE)
        onCreate(p0)
    }

    fun login(userName: String, password: String): Int? {
        try {
            val mSQLiteDatabase = this.readableDatabase
            val script = "select * from $TABLE_NAME_USER where UserName= $userName and Password=$password"
            val cursor = mSQLiteDatabase.rawQuery(script, null)
            if (cursor.count == 1) {
                cursor.moveToFirst()
                val userId = cursor.getInt(cursor.getColumnIndex(USER_ID))
                cursor.close()
                return userId
            }
        } catch (exp: Exception) {
            exp.printStackTrace()
        }
        return null
    }

    fun register(user: User): Boolean {
        try {
            if (isUserNameExist(user.userName)) {
                return false
            }
            val mSQLiteDatabase = this.writableDatabase
            val contentValue = ContentValues()
            contentValue.put(USER_NAME, user.userName)
            contentValue.put(USER_PASSWORD, user.password)
            contentValue.put(USER_IMG_PATH, user.imagePath)
            mSQLiteDatabase.insert(TABLE_NAME_USER, null, contentValue)
            return true
        } catch (exp: Exception) {
            exp.printStackTrace()
        }
        return false
    }

    private fun isUserNameExist(userName: String): Boolean {
        try {
            val mSQLiteDatabase = this.readableDatabase
            val script = "select * from $TABLE_NAME_USER where $USER_NAME=$userName"
            val cursor = mSQLiteDatabase.rawQuery(script, null)
            if (cursor.count == 0) {
                cursor.close()
                return false
            }
        } catch (exp: Exception) {
            exp.printStackTrace()
        }
        return true
    }

    fun getUser(userId: Int): User? {
        try {
            val sqLiteDatabase = this.readableDatabase
            val script = "select * from $TABLE_NAME_USER where $USER_ID=$userId"
            val cursor = sqLiteDatabase.rawQuery(script, null)
            if (cursor.count != 1) {
                return null
            }
            cursor.moveToFirst()
            val user = User(cursor.getInt(cursor.getColumnIndex(USER_ID)),
                    cursor.getString(cursor.getColumnIndex(USER_NAME)),
                    cursor.getString(cursor.getColumnIndex(USER_PASSWORD)),
                    cursor.getString(cursor.getColumnIndex(USER_IMG_PATH)))
            cursor.close()
            return user
        } catch (exp: Exception) {
            exp.printStackTrace()
        }
        return null
    }

    fun editUser(user : User) : Boolean{
        try{
            val mSQLiteDatabase = this.writableDatabase
            val contentValues = ContentValues()
            contentValues.put(USER_PASSWORD, user.password)
            contentValues.put(USER_IMG_PATH, user.imagePath)
            mSQLiteDatabase.update(TABLE_NAME_USER, contentValues, "$USER_ID=?", arrayOf(user.userId.toString()))
            return true
        } catch (exp : Exception){
            exp.printStackTrace()
        }
        return false
    }

    fun getNotes(userId: Int, status: Int): MutableList<Note>? {
        try {
            val mSQLiteDatabase = this.readableDatabase
            val script = "select * from $TABLE_NAME_NOTES where $USER_ID=$userId and $NOTE_STATUS=$status"
            val cursor = mSQLiteDatabase.rawQuery(script, null)
            if (cursor.count == 0) {
                return null
            }
            val noteList = ArrayList<Note>()
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                noteList.add(Note(cursor.getInt(cursor.getColumnIndex(NOTE_ID)),
                        cursor.getInt(cursor.getColumnIndex(USER_ID)),
                        cursor.getString(cursor.getColumnIndex(NOTE_TODO)),
                        cursor.getInt(cursor.getColumnIndex(NOTE_STATUS))))
                cursor.moveToNext()
            }
            return noteList
        } catch (exp: Exception) {
            exp.printStackTrace()
            return null
        }
    }

    fun getNote(noteId: Int): Note? {
        try {
            val mSQLiteDatabase = this.writableDatabase
            val script = "select * from $TABLE_NAME_NOTES where $NOTE_ID = $noteId"
            val cursor = mSQLiteDatabase.rawQuery(script, null)
            if (cursor.count != 1) {
                return null
            }
            cursor.moveToFirst()
            val note = Note(cursor.getInt(cursor.getColumnIndex(NOTE_ID)),
                    cursor.getInt(cursor.getColumnIndex(USER_ID)),
                    cursor.getString(cursor.getColumnIndex(NOTE_TODO)),
                    cursor.getInt(cursor.getColumnIndex(NOTE_STATUS)))
            cursor.close()
            return note
        } catch (exp: Exception) {
            exp.printStackTrace()
        }
        return null
    }

    fun addNote(note: Note): Boolean {
        try {
            val mSQLiteDatabase = this.writableDatabase
            val contentValues = ContentValues()
            contentValues.put(USER_ID, note.userId)
            contentValues.put(NOTE_TODO, note.todo)
            contentValues.put(NOTE_STATUS, note.status)
            mSQLiteDatabase.insert(TABLE_NAME_NOTES, null, contentValues)
            return true
        } catch (exp: Exception) {
            exp.printStackTrace()
        }
        return false
    }

    fun deleteNote(noteId: Int): Boolean {
        try {
            val mSQLiteDatabase = this.writableDatabase
            mSQLiteDatabase.delete(TABLE_NAME_NOTES, "$NOTE_ID=?", arrayOf(noteId.toString()))
            return true
        } catch (exp: Exception) {
            exp.printStackTrace()
        }
        return false
    }

    fun editNote(note: Note): Boolean {
        try {
            val mSQLiteDatabase = this.writableDatabase
            val contentValues = ContentValues()
            contentValues.put(NOTE_TODO, note.todo)
            contentValues.put(NOTE_STATUS, note.status)
            mSQLiteDatabase.update(TABLE_NAME_NOTES, contentValues, "$NOTE_ID=?", arrayOf(note.id.toString()))
            return true
        } catch (exp: Exception) {
            exp.printStackTrace()
        }
        return false
    }
}