package com.hussein.startup

import android.content.ContentValues
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_notes.*

class AddNotes : AppCompatActivity() {
    var dbManager:NotesDatabase?=null
    var id=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)
        dbManager = DBManager().getDatabase(applicationContext)



       try{
           var bundle:Bundle= intent.extras!!
           id=bundle.getInt("ID",0)
           if(id!=0) {
               etTitle.setText(bundle.getString("name") )
               etDes.setText(bundle.getString("des") )

           }
       }catch (ex:Exception){}


    }

    fun  buAdd(view:View){

        var values= ContentValues()
        values.put("Title",etTitle.text.toString())
        values.put("Description",etDes.text.toString())


        if(id==0) {

             dbManager!!.NotesDao().insert( Notes(0,etTitle.text.toString(),etDes.text.toString()))
             finish()

        }else{

                dbManager!!.NotesDao().update( etTitle.text.toString(),etDes.text.toString(),id)
                finish()

        }

    }
}


