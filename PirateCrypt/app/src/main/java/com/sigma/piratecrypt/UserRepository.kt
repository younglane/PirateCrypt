/*
Class is not in use. Used for accessing the DB of users. Haven't implemented yet.
-Adam Wainright
 */

package com.sigma.piratecrypt

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.sigma.piratecrypt.database.UserDatabase
import com.sigma.piratecrypt.database.migration_2_3
import java.lang.IllegalStateException
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "user-database"

class UserRepository private constructor(context: Context){

    private val database : UserDatabase = Room.databaseBuilder(
        context.applicationContext,
        UserDatabase::class.java,
        DATABASE_NAME
    ).addMigrations(migration_2_3).build()

    private val userDao = database.userDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getUsers(): LiveData<List<User>> = userDao.getUsers()
    fun getUser(id: UUID): LiveData<User?> = userDao.getUser(id)
    fun getUserByName(name: String): LiveData<User?> {

        val user: LiveData<User?> = userDao.getUserByName(name)


        Log.d("User Repo: ","name referred: $name")
        Log.d("User Repo: ","user returned: ${user.value}")

        return userDao.getUserByName(name)
    }

    fun updateUser(user: User) {
        executor.execute {
            userDao.updateUser(user)
        }
    }

    fun addUser(user: User) {
        executor.execute {
            Log.d("UserRepo","Add User was called")
            userDao.addUser(user)
        }
    }

    companion object{
        private var INSTANCE: UserRepository? = null

        fun initialize(context: Context){
            if(INSTANCE ==null){
                INSTANCE = UserRepository(context)
            }
        }
        fun get(): UserRepository{
            return INSTANCE?:
            throw IllegalStateException("User Repo must be initialized")
        }
    }

}