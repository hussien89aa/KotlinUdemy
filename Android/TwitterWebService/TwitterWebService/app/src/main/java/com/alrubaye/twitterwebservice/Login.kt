package com.alrubaye.twitterwebservice

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL


class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }


    fun buLoginEvent(view:View){
        // user login
        val url="http://10.0.2.2/~hussienalrubaye/TwitterAndroidServer/Login.php?email=" + etEmail.text.toString() +"&password="+ etPassword.text.toString()
        MyAsyncTask().execute(url)
    }

    fun buRegisterUserEvent(view:View){
        val intent=Intent(this,Register::class.java)
        startActivity(intent)
    }


    // CALL HTTP
    inner class MyAsyncTask: AsyncTask<String, String, String>() {

        override fun onPreExecute() {
            //Before task started
        }
        override fun doInBackground(vararg p0: String?): String {
            try {

                val url= URL(p0[0])

                val urlConnect=url.openConnection() as HttpURLConnection
                urlConnect.connectTimeout=7000
                val op=Operations()
                var inString= op.ConvertStreamToString(urlConnect.inputStream)
                //Cannot access to ui
                publishProgress(inString)
            }catch (ex:Exception){}


            return " "

        }

        override fun onProgressUpdate(vararg values: String?) {
            try{
                var json= JSONObject(values[0])


                if (json.getString("msg")== "pass login"){
                    val userInfo =JSONArray(json.getString("info"))
                    val userCredentails= userInfo.getJSONObject(0)

                    Toast.makeText(applicationContext,userCredentails.getString("first_name"), Toast.LENGTH_LONG).show()

                    val user_id= userCredentails.getString("user_id")
                    val saveSettings= SaveSettings(applicationContext)
                    saveSettings.saveSettings(user_id)
                    finish()
                }else{
                    Toast.makeText(applicationContext,json.getString("msg"), Toast.LENGTH_LONG).show()
                }

            }catch (ex:Exception){}
        }

        override fun onPostExecute(result: String?) {

            //after task done
        }


    }


}
