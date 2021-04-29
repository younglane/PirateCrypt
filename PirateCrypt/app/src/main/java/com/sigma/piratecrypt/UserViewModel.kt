/*
View Model for the create user fragment... add user isn't working right now.
-Adam Wainright
 */

package com.sigma.piratecrypt

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.*

class UserViewModel : ViewModel() {

    private val userRepository = UserRepository.get()
    private val userNameLiveData = MutableLiveData<String>()
    private val userNameLiveDatax = MutableLiveData<String>()

    var userLiveData: LiveData<User?> =
            Transformations.switchMap(userNameLiveData){userName ->
                userRepository.getUserByName(userName)

            }

    var userLiveDatax: LiveData<User?> =
            Transformations.switchMap(userNameLiveDatax){userName ->
                userRepository.getUserByName(userName)

            }


    fun addUser(user: User) {
        userRepository.addUser(user)

    }

    fun getUserByName(userName: String) {
        Log.d("getUserByName Triggered", "$userName")
        userNameLiveData.value = userName

    }
    fun getUserByNamex(userName: String) {
        Log.d("getUserByName Triggered", "$userName")
        userNameLiveDatax.value = userName

    }

    fun saveUser(user: User) {
        userRepository.updateUser(user)
    }
    fun getUser(userId: UUID){

    }
    // How to verify the username is unique. Not working because ass user isn't working.
    /*private fun verifyUnique(user: User): Boolean{
        val name: String = user.name
        return userRepository.getUserByName(name) == null
    }*/
}