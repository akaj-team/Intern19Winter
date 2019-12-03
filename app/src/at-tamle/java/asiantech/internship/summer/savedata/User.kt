package asiantech.internship.summer.savedata

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues

class User(val context: Context) {
    private var DB: Database = Database(context)
    private var sqLiteDatabase: SQLiteDatabase? = null

    private fun open() {
        sqLiteDatabase = DB.getWritableDatabase()
    }

    private fun close() {
        DB.close()
    }

    fun isInvalid(username: String): Boolean {
        open()
        val cursor = sqLiteDatabase?.rawQuery("select * from ${Database.TBL_USER} where ${Database.US_USERNAME} = '${username}'", null)
        val count = cursor?.count
        cursor?.close()
        close()
        return count != 0
    }

    fun insert(dataUser: DataUser): Boolean {
        open()
        val values = ContentValues()
        values.put(Database.US_USERNAME, dataUser.userName)
        values.put(Database.US_PASSWORD, dataUser.password)
        values.put(Database.US_NICKNAME, dataUser.nickname)
        values.put(Database.US_PHOTO_PATH, dataUser.photoPath)
        val result = sqLiteDatabase?.insert(Database.TBL_USER, null, values)
        close()

        return result?.toDouble() != 0.0
    }

    fun isExisted(username: String): Boolean? {
        open()
        val cursor = sqLiteDatabase?.rawQuery("select * from ${Database.TBL_USER} where ${Database.US_USERNAME} = '${username}'", null)
        val result = cursor?.moveToFirst()
        cursor?.close()
        close()

        return result
    }

    fun getUser(idUser: Int): DataUser? {
        open()
        var dataUser: DataUser? = null
        val cursor = sqLiteDatabase!!.rawQuery("select * from ${Database.TBL_USER} where ${Database.US_ID} = ${idUser}", null)
        if (cursor.moveToFirst()) {
            dataUser = DataUser()
            dataUser.id = cursor.getInt(cursor.getColumnIndex(Database.US_ID))
            dataUser.userName = cursor.getString(cursor.getColumnIndex(Database.US_USERNAME))
            dataUser.password = cursor.getString(cursor.getColumnIndex(Database.US_PASSWORD))
            dataUser.nickname = cursor.getString(cursor.getColumnIndex(Database.US_NICKNAME))
            dataUser.photoPath = cursor.getString(cursor.getColumnIndex(Database.US_PHOTO_PATH))
        }
        cursor.close()
        close()

        return dataUser
    }

    fun getUser(username: String, password: String): DataUser? {
        open()
        var dataUser: DataUser? = null
        val select = "select * from ${Database.TBL_USER} where ${Database.US_USERNAME} = '${username}' and ${Database.US_PASSWORD} = '${password}'"
        val cursor = sqLiteDatabase!!.rawQuery(select, null)
        if (cursor.moveToFirst()) {
            dataUser = DataUser()
            dataUser.id = cursor.getInt(cursor.getColumnIndex(Database.US_ID))
            dataUser.userName = cursor.getString(cursor.getColumnIndex(Database.US_USERNAME))
            dataUser.password = cursor.getString(cursor.getColumnIndex(Database.US_PASSWORD))
            dataUser.nickname = cursor.getString(cursor.getColumnIndex(Database.US_NICKNAME))
            dataUser.photoPath = cursor.getString(cursor.getColumnIndex(Database.US_PHOTO_PATH))
        }
        cursor.close()
        close()
        return dataUser
    }

    fun update(dataUser: DataUser): Boolean {
        open()
        val values = ContentValues()
        values.put(Database.US_NICKNAME, dataUser.nickname)
        values.put(Database.US_PASSWORD, dataUser.password)
        val result = sqLiteDatabase!!.update(Database.TBL_USER, values, "${Database.US_ID} = ${dataUser.id}", null)
        close()

        return result != 0
    }
}