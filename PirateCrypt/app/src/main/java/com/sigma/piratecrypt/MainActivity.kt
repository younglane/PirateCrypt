/*
This is where all the magic happens. I am using Fragments instead of
a bunch of activities. That gets sloppy. I have three fragments, the initial
fragment that is loaded is the LoginFragment, as you see in the onCreate function.
If a user logs in with the correct credentials, onLoginSuccess is triggered and
the fragment manager loads the Userfragment. If a user selects create, onCreateUser
is triggered and CreateUserFragment is loaded. Don't get overwhelmed. RESEARCH ROOM DB.
-Adam Wainright :)
 */

package com.sigma.piratecrypt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(),LoginFragment.Callbacks,CreateUserFragment.Callbacks {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment == null){
            val fragment = LoginFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container,fragment)
                .commit()
        }

    }
    override fun onLoginSuccess(userId: String){
        val fragment = UserFragment.newInstance(userId)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
    override fun onCreateUser() {
        val fragment = CreateUserFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onCreatedUser() {
        val fragment = LoginFragment.newInstance()
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
    }
}