package com.hussein.startup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import  kotlinx.android.synthetic.main.activity_main.*
import java.util.*


open class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        buGetAge.setOnClickListener( View.OnClickListener {


        }
        */
    }


  fun  BuClickEvent(view:View){

     // fire click button
     val userDOB:String=etDOB.text.toString()
      if (userDOB.toInt()==0){
          tvShowAge.text=" invalid input"
          return
      }
     val year:Int=Calendar.getInstance().get(Calendar.YEAR)
     val myAge=year - userDOB.toInt()
      val avg =  myAge/userDOB.toInt()
      tvShowAge.text = "Your age is "+ myAge
 }

}
