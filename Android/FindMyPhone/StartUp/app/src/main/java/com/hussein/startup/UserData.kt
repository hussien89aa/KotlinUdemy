package com.hussein.startup

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences

class  UserData{
    var context:Context?=null
    var sharedRef:SharedPreferences?=null
    constructor(context:Context){
        this.context=context
        this.sharedRef= context.getSharedPreferences("userData",Context.MODE_PRIVATE)
    }

    fun savePhone(phoneNumber:String){

        val editor=sharedRef!!.edit()
        editor.putString("phoneNumber",phoneNumber)
        editor.commit()
    }

    fun loadPhoneNumber():String{

        val phoneNumber =sharedRef!!.getString("phoneNumber","empty")
        return  phoneNumber!!
    }

    fun isFirstTimeLoad(){

        val phoneNumber =sharedRef!!.getString("phoneNumber","empty")
        if ( phoneNumber.equals("empty")){
            val intent =Intent(context,Login::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context!!.startActivity(intent)
        }


    }

    fun saveContactInfo(){
      var listOfTrackers=""
        for ((key,value) in myTrackers){

            if (listOfTrackers.length ==0 ){
                listOfTrackers = key + "%" + value
            }else{
                listOfTrackers += "%"+ key + "%" + value
            }
        }

        if (listOfTrackers.length ==0 ){
            listOfTrackers ="empty"
        }


        val editor=sharedRef!!.edit()
        editor.putString("listOfTrackers",listOfTrackers)
        editor.commit()

    }


    fun loadContactInfo(){
        myTrackers.clear()
        val listOfTrackers =sharedRef!!.getString("listOfTrackers","empty")

        if (!listOfTrackers.equals("empty")){
            val usersInfo=listOfTrackers!!.split("%").toTypedArray()
            var i=0
            while(i<usersInfo.size){

                myTrackers.put(usersInfo[i],usersInfo[i+1])
                i += 2
            }
        }
    }

    companion object {
        var myTrackers: MutableMap<String,String> = HashMap()
        fun formatPhoneNumber(phoneNumber:String):String {
            var onlyNumber= phoneNumber.replace("[^0-9]".toRegex(),"")
            if (phoneNumber[0]== '+') {
                onlyNumber ="+"+ phoneNumber
            }

            return  onlyNumber
        }
    }

}
