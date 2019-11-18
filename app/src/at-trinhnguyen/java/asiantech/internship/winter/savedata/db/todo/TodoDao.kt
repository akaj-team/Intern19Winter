package asiantech.internship.winter.savedata.db.todo

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo_table WHERE idUser=:idUser AND completed= 0 ORDER BY idTodo DESC")
    fun getTodos(idUser: Long): LiveData<List<Todo>>

    @Query("SELECT * FROM todo_table WHERE idUser=:idUser AND completed= 1 ORDER BY idTodo DESC")
    fun getDones(idUser: Long): LiveData<List<Todo>>

    @Query("SELECT * FROM todo_table WHERE idTodo=:idTodo")
    fun getTodoById(idTodo: Long): Todo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Todo)

    @Update
    fun updateTodo(todo: Todo)

    @Query("UPDATE todo_table SET completed=:isCompleted WHERE idTodo=:idTodo ")
    suspend fun updateCompletedById(idTodo: Long, isCompleted: Boolean)

    @Query("DELETE FROM todo_table")
    fun deleteAll()

    @Query("DELETE FROM todo_table WHERE idTodo= :idTodo")
    suspend fun deleteTodoById(idTodo: Long)

    @Query("DELETE FROM todo_table WHERE completed=1")
    fun deleteCompletedTodo(): Int
}
