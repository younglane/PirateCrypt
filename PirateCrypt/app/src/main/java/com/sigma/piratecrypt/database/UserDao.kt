/*
Database file for SQL queries.
Directly relates to the user repository.
-Adam Wainright
 */

package com.sigma.piratecrypt.database

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.sigma.piratecrypt.User
import java.util.*

@Dao
interface UserDao {

    @Query("SELECT * FROM User")
    fun getUsers() : LiveData<List<User>>

    @Query("SELECT * FROM User WHERE id=(:id)")
    fun getUser(id: UUID) : LiveData<User?>
    //This query searches the DB for a user name... May need to change it IDK.
    //Adam Wainright
    @Query("SELECT * FROM User WHERE name LIKE (:name)")
    fun getUserByName(name: String) : LiveData<User?>


    @Update
    fun updateUser(user: User)

    @Insert
    fun addUser(user: User)

}