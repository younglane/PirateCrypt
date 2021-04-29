package com.sigma.piratecrypt
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import java.util.*


class LoginFragment: Fragment() {
    //Used to send data back to main activity, as you see later down in the code it isn't
    //to complicated. MainActivity Imports this and will have access to these functions.
    interface Callbacks{
        //When the DB is implemented, we will send this back to
        // main activity so we will know what user to display.
        //fun onLoginSuccess(userId: UUID)
        fun onLoginSuccess(userId: String)
        fun onCreateUser()
    }

    private lateinit var user: User

    //reference to the list view model, not used rn. How it talks to the DB in lamens terms...
    private val userViewModel: UserViewModel by lazy{
        ViewModelProviders.of(this).get(UserViewModel::class.java)
    }

    private var callbacks: Callbacks? = null
    private lateinit var userName: String
    private lateinit var name: EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button
    private lateinit var createButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = User()

    }
    companion object {
        fun newInstance() : LoginFragment {
            return LoginFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }
    //setting up the view and variables. R.layout.whatever is the internal id given to the xml items.
    //similar to HTML. See the associated xml files...
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View? =  inflater.inflate(R.layout.fragment_login,container,false)
        name = view?.findViewById(R.id.UserName) as EditText
        password = view!!.findViewById(R.id.Password) as EditText
        loginButton = view!!.findViewById(R.id.loginButton) as Button
        createButton = view!!.findViewById(R.id.createAccount) as Button

        createButton.setOnClickListener{view: View ->
            callbacks?.onCreateUser()
        }
        loginButton.setOnClickListener{
            validate(name.text.toString(),password.text.toString())}

        return view
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel.userLiveData.observe(
                viewLifecycleOwner,
                Observer { user ->
                    user?.let {
                        this.user = user
                        Log.d("Result: ","$user")
                    }
                }
        )
    }

    override fun onStart() {
        super.onStart()
        val userNameWatcher = object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            //This updates user.name every time the text is changed... It was breaking the login feature.
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //user.name = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
                userName = name.text.toString()
                userViewModel.getUserByName(userName)
            }
        }
        name.addTextChangedListener(userNameWatcher)

    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }


    //my function checking for a generic login credential. This is the only one that works for now
    //In here we would search the DB, I think by a viewmodel but I am currently looking into that.

    private fun validate(userName: String, pass: String){
        Log.d("Username:","${user.name}")
        if(user.name == ""){
            Log.d("Validate:", "Incorrect Username")
        }
        else if(user.name == userName && user.password == pass){
            Log.d("Validate:", "Login Validated")
            //this callback sends the correct username back to main activity.
            //Need the make the user fragment load this user from the db
            //with the live data observer similar to how I did it up top.
            Log.d("Username:","${user.name}")
            password.text.clear()
            name.text.clear()
            callbacks?.onLoginSuccess(user.name)

        }else{
            Log.d("Validate:", "Incorrect Username")

        }
        }








}

