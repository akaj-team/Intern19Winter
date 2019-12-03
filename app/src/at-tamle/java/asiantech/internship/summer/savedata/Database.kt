package asiantech.internship.summer.savedata

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        const val DB_NAME = "quanlyuser"
        const val DB_VERSION = 1
        const val TBL_USER = "USER"
        const val US_ID = "Id"
        const val US_USERNAME = "Username"
        const val US_NICKNAME = "Nickname"
        const val US_PASSWORD = "Password"
        const val US_PHOTO_PATH = "Path"
        const val TBL_TODO = "TODO"
        const val TD_ID = "IdTodo"
        const val TD_IDUSER = "IdUser"
        const val TD_TODO = "Todo"
        const val TD_STATUS = "Status"
    }

    override fun onCreate(sqLiteDatabase: SQLiteDatabase?) {
        sqLiteDatabase?.execSQL("create table ${TBL_USER} (${US_ID} integer primary key autoincrement, ${US_USERNAME} text, ${US_NICKNAME} text, ${US_PASSWORD} text, ${US_PHOTO_PATH} text)")
        sqLiteDatabase?.execSQL("create table ${TBL_TODO} (${TD_ID} integer primary key autoincrement, ${TD_IDUSER} integer, ${TD_TODO} text, ${TD_STATUS} integer)")
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion != newVersion) {
            sqLiteDatabase?.execSQL("DROP TABLE IF EXISTS ${TBL_TODO}");
            sqLiteDatabase?.execSQL("DROP TABLE IF EXISTS ${TBL_USER}");
            onCreate(sqLiteDatabase);
        }
    }
}
