//Testing..... Same as other fragments.

package com.sigma.piratecrypt

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_user.*
import kotlinx.android.synthetic.main.list_item_message.*
import java.util.concurrent.Executors
import com.sigma.piratecrypt.encryption.Crypto


class UserFragment : Fragment() {
    private val executor = Executors.newSingleThreadExecutor()
    private lateinit var user: User
    private lateinit var userx: String
    private lateinit var toUser: User
    private lateinit var name: TextView
    private lateinit var inbox: TextView
    private lateinit var sendTo: EditText
    private lateinit var messageContent: EditText
    private lateinit var sendButton: Button
    private val userViewModel: UserViewModel by lazy{
        ViewModelProviders.of(this).get(UserViewModel::class.java)
    }
    private val userViewModelx: UserViewModel by lazy{
        ViewModelProviders.of(this).get(UserViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = User()
        toUser = User()




    }


    companion object {
        fun newInstance(userId: String): UserFragment {
            val args = Bundle().apply {
                putSerializable("user_name", userId)
            }
            return UserFragment().apply {
                arguments = args
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_user,container,false)
        inbox = view?.findViewById(R.id.inbox) as TextView
        messageContent= view?.findViewById(R.id.messageContent) as EditText
        sendTo = view?.findViewById(R.id.sendMessage) as EditText
        sendButton = view?.findViewById(R.id.sendButton) as Button
        name = view?.findViewById(R.id.name) as TextView
        sendButton.setOnClickListener{
            sendMessage(messageContent.text.toString())}
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel.userLiveData.observe(
                viewLifecycleOwner,
                Observer { user ->
                    user?.let {
                        this.user = user
                        updateUI()

                    }
                }
        )
        userViewModelx.userLiveDatax.observe(
            viewLifecycleOwner,
            Observer { userx ->
                userx?.let {
                    this.toUser = userx

                }
            }
        )






        val userId = arguments?.getSerializable("user_name") as String

        userViewModel.getUserByName(userId)


    }
    private fun updateUI(){

        name.text = user.name
        if(user.messages.isEmpty()){
            inbox.text = "No Messages"
        }else{
            val display: MutableList<String> = mutableListOf()
            val messagesIter : MutableIterator<String> = user.messages.listIterator()
            var encrypted : String
            var keySTR : String
            var decrypted : String
            while(messagesIter.hasNext()){

                encrypted = messagesIter.next()
                keySTR = messagesIter.next()
                decrypted = ("\n" + Crypto(keySTR).decrypt(encrypted))
                decrypted = decrypted.replace('�', ' ')
                display.add(decrypted)

            }
            inbox.text = display.toString().substring(2, display.toString().length-1)
        }/*else{
            inbox.text = user.messages.toString()
        }*/






    }
    private fun sendMessage(message: String){

        val cypher : Crypto = Crypto()
        val encrypted : String = cypher.encrypt("From " + user.name + ": " + message)
        toUser.messages.add(encrypted)
        val keySTR : String = cypher.getKey().toString()
        val key : String = keySTR.substring(1, keySTR.length-1)
        toUser.messages.add(key)
        //toUser.messages.add("\n"+user.name+":  " + message)
        Log.d("Send Message", "Sending TO: ${toUser.name}")
        userViewModelx.saveUser(toUser)
        sendTo.text.clear()
        messageContent.text.clear()




    }

    override fun onStart() {
        super.onStart()
        inbox.movementMethod= ScrollingMovementMethod()
        val userNameWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            //This updates user.name every time the text is changed... It was breaking the login feature.
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                userx = sendTo.text.toString()
                userViewModelx.getUserByNamex(userx)
            }
        }
        sendTo.addTextChangedListener(userNameWatcher)


    }



}