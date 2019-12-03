package asiantech.internship.summer.savedata

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues

class Todo(context: Context) {
    private val DB: Database = Database(context)
    private var sqLiteDatabase: SQLiteDatabase? = null

    private fun open() {
        sqLiteDatabase = DB.getWritableDatabase()
    }

    private fun close() {
        DB.close()
    }

    fun insert(dataTodo: DataTodo): Boolean {
        open()
        val values = ContentValues()
        values.put(Database.TD_IDUSER, dataTodo.idUser)
        values.put(Database.TD_TODO, dataTodo.todo)
        values.put(Database.TD_STATUS, dataTodo.status)
        val result = sqLiteDatabase?.insert(Database.TBL_TODO, null, values)
        close()

        return result?.toDouble() != 0.0
    }

    fun getlist(idUser: Int, isDone: Int): MutableList<DataTodo> {
        open()
        val ds = mutableListOf<DataTodo>()
        val select = "select * from ${Database.TBL_TODO} where ${Database.TD_IDUSER} = ${idUser} and ${Database.TD_STATUS} = ${isDone}"
        val cursor = sqLiteDatabase?.rawQuery(select, null)

        cursor?.moveToFirst()
        while (cursor?.isAfterLast == false) {
            val todo = DataTodo()
            todo.id = cursor.getInt(cursor.getColumnIndex(Database.TD_ID))
            todo.idUser = cursor.getInt(cursor.getColumnIndex(Database.TD_IDUSER))
            todo.todo = cursor.getString(cursor.getColumnIndex(Database.TD_TODO))
            todo.status = cursor.getInt(cursor.getColumnIndex(Database.TD_STATUS))
            ds.add(todo)
            cursor.moveToNext()
        }
        cursor?.close()
        close()

        return ds
    }

    fun updateStatus(idTodo: Int, idUser: Int, isDone: Int): Boolean {
        open()
        val values = ContentValues()
        values.put(Database.TD_STATUS, isDone)
        val whereClause = "${Database.TD_ID} = ${idTodo} and ${Database.TD_IDUSER} = ${idUser}"
        val result = sqLiteDatabase?.update(Database.TBL_TODO, values, whereClause, null)
        close()

        return result != 0
    }

    fun update(dataTodo: DataTodo): Boolean {
        open()
        val values = ContentValues()
        values.put(Database.TD_TODO, dataTodo.todo)
        val result = sqLiteDatabase?.update(Database.TBL_TODO, values, "${Database.TD_ID} = ${dataTodo.id}", null)
        close()

        return result != 0
    }

    fun delete(dataTodo: DataTodo): Boolean {
        open()
        val result = sqLiteDatabase?.delete(Database.TBL_TODO, "${Database.TD_ID} = ${dataTodo.id}", null)
        close()

        return result != 0
    }
}
