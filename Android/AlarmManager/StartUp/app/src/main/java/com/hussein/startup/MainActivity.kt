package com.hussein.startup

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import  kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val saveData=SaveData(applicationContext)

        tvShowTime.text= saveData.getHour().toString() + ":" + saveData.getMinute().toString()
    }


    fun BuSetTime(view:View){
        val popTime= PopTime()
        val fm=fragmentManager
        popTime.show(fm,"Select time")


    }

    fun SetTime(Hours:Int,Minute:Int){

        tvShowTime.text= Hours.toString() + ":" + Minute.toString()

        val saveData=SaveData(applicationContext)
        saveData.SaveData(Hours,Minute)
        saveData.setAlarm()


    }
}
