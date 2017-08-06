package com.alrubaye.twitterwebservice

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences

/**
 * Created by hussienalrubaye on 8/6/17.
 */
class SaveSettings{
    var context:Context?=null
    var sharedRef:SharedPreferences?=null
    constructor(context:Context){
        this.context=context
        sharedRef=context.getSharedPreferences("myRef",Context.MODE_PRIVATE)
    }

    fun saveSettings(userID:String){
        val editor=sharedRef!!.edit()
        editor.putString("userID",userID)
        editor.commit()
        loadSettings()
    }

    fun loadSettings(){
        userID= sharedRef!!.getString("userID","0")

        if (userID=="0"){
            val intent=Intent(context,Login::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context!!.startActivity(intent)
        }
    }


    companion object {
        var userID=""
    }
}