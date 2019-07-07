package com.hussein.startup


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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
        val fm=supportFragmentManager
        popTime.show(fm,"Select time")


    }

    fun SetTime(Hours:Int,Minute:Int){

        tvShowTime.text= "$Hours:$Minute"

        val saveData=SaveData(applicationContext)
        saveData.SaveData(Hours,Minute)
        saveData.setAlarm()


    }
}
