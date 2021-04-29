/*
Figured we may need this for the login fragment
so when the user logs in the fragment can search the DB for the username
and verify credentials..
 */

package com.sigma.piratecrypt

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class UserListViewModel : ViewModel() {

    private val userRepository = UserRepository.get()
    val userListLiveData = userRepository.getUsers()

}
