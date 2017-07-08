package com.hussein.startup

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*


class Login : AppCompatActivity() {
    var mAuth:FirebaseAuth?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth= FirebaseAuth.getInstance()
        signInAnonymously()
    }


    fun signInAnonymously(){
        mAuth!!.signInAnonymously()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(applicationContext, "Authentication success.",
                                Toast.LENGTH_SHORT).show()
                        val user = mAuth!!.getCurrentUser()

                    } else {
                         Toast.makeText(applicationContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()

                    }
                }
    }

    fun buRegisterEvent(view:View){

        val userData=UserData(this)
        userData.savePhone(etPhoneNumber.text.toString())


        // get datatime
        val df =SimpleDateFormat("yyyy/MMM/dd HH:MM:ss")
        val date =Date()
        // save to database
        val mDatabase =FirebaseDatabase.getInstance().reference
        mDatabase.child("Users").child(etPhoneNumber.text.toString()).child("request").setValue(df.format(date).toString())
        mDatabase.child("Users").child(etPhoneNumber.text.toString()).child("Finders").setValue(df.format(date).toString())

        finish()
    }
}
