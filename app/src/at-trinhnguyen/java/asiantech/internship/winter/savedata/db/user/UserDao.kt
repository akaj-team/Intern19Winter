package asiantech.internship.winter.savedata.db.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table")
    fun getUsers(): LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE email=:email AND password=:password ")
    fun login(email: String, password: String): LiveData<List<User>>
    
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()

    @Query("DELETE FROM user_table WHERE idUser= :idUser")
    suspend fun deleteUserById(idUser: Long)

    @Query("SELECT COUNT(username) FROM user_table WHERE username=:username")
    suspend fun getNoUsername(username: String): Int

    @Query("SELECT COUNT(email) FROM user_table WHERE username=:username")
    suspend fun getNoEmail(username: String): Int

}
