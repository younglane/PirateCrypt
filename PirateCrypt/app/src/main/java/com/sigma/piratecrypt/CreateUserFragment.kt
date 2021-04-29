/*
This is the Created user Fragment. When you click the create button
on the login page, the callbacks function pings the MainActivity and tells
it to load up the fragment_create_user.xml view and associated .kt file.
Function in MainActivity is Called onCreateUser.
This fragment has no functionality yet other than displaying
for testing purposes.
-Adam Wainright
 */

package com.sigma.piratecrypt

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders


private const val TAG = "CreateUser"

class CreateUserFragment: Fragment() {

    interface Callbacks{
        //When the DB is implemented, we will send this back to
        // main activity so we will know what user to display.
        //fun onLoginSuccess(userId: UUID)
        fun onCreatedUser()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    private var callbacks: Callbacks? = null

    private lateinit var newUser: EditText
    private lateinit var pass: EditText
    private lateinit var verify: EditText
    private lateinit var create: Button

    //reference to the view model
    private val createUserViewModel: UserViewModel by lazy{
        ViewModelProviders.of(this).get(UserViewModel::class.java)
    }

    //Common practice to include this.. called in MainActivity when the fragment is loaded.
    companion object {
        fun newInstance() : CreateUserFragment {
            return CreateUserFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //this is describing what xml file to show on the screen. IE fragment_created_user.xml
        val view: View? = inflater.inflate(R.layout.fragment_create_user, container, false)

        newUser = view?.findViewById(R.id.newUser) as EditText
        pass = view?.findViewById(R.id.newPassword) as EditText
        verify = view?.findViewById(R.id.passVerify) as EditText
        create = view?.findViewById(R.id.create) as Button
        create.setOnClickListener{
            validate(newUser.text.toString(),pass.text.toString(), verify.text.toString())}
        return view

    }
    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private fun validate(newUser: String,pass: String, verify: String){
        var flag: Boolean = true

        //not sure about this run thing, made me put it here.

        if(pass == verify) run {
            val user = User()
            user.name = newUser
            user.password = pass
            Log.d("New user: "," $user")
            createUserViewModel.addUser(user)
        }
        if(flag){
            Log.d(TAG,"User was created, not screen transitioning")
            callbacks?.onCreatedUser()

        }else{Log.d(TAG,"Flag is false")}
    }


}