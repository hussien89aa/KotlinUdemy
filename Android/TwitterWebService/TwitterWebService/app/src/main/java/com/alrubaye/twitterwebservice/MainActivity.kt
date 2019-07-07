package com.alrubaye.twitterwebservice

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SearchView
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.add_ticket.view.*
import kotlinx.android.synthetic.main.tweets_ticket.view.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    var ListTweets=ArrayList<Ticket>()
    var adpater:MyTweetAdpater?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val saveSettings= SaveSettings(this)
        saveSettings.loadSettings()

        // set adpater
        adpater= MyTweetAdpater(this,ListTweets)
        lvTweets.adapter=adpater



        SearchInDatabase("%",0)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)

        val sv:SearchView = menu.findItem(R.id.app_bar_search).actionView as SearchView

        val sm= getSystemService(Context.SEARCH_SERVICE) as SearchManager
        sv.setSearchableInfo(sm.getSearchableInfo(componentName))
        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(applicationContext, query, Toast.LENGTH_LONG).show()
                SearchInDatabase(query,0)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })


        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item != null) {
            when(item.itemId){
                R.id.homePage->{
                    // TODO: go to home
                    // CALL http
                    SearchInDatabase("%",0)

                }
            }
        }

        return super.onOptionsItemSelected(item)
    }


    fun SearchInDatabase(SearchText:String, startFrom:Int){
        val SearchText = URLEncoder.encode(SearchText,"utf-8")
        DownloadURL= URLEncoder.encode(DownloadURL,"utf-8")
        val url="http://10.0.2.2/~hussienalrubaye/TwitterAndroidServer/TweetList.php?op=3&query=" + SearchText + "&StartFrom=" + startFrom
        MyAsyncTask().execute(url)
    }

    // dapter loading
    inner class  MyTweetAdpater:BaseAdapter{
        var listNotesAdpater=ArrayList<Ticket>()
        var context:Context?=null
        constructor(context:Context, listNotesAdpater:ArrayList<Ticket>):super(){
            this.listNotesAdpater=listNotesAdpater
            this.context=context
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {


            var mytweet=listNotesAdpater[p0]

            if(mytweet.tweetDate.equals("add")) {
                var myView = layoutInflater.inflate(R.layout.add_ticket, null)

                myView.iv_attach.setOnClickListener( {
                    loadImage()

                })

                myView.iv_post.setOnClickListener( {
                    //upload server

                    ListTweets.add(0,Ticket("0","him","url","loading","","",""))
                    adpater!!.notifyDataSetChanged()

                    // CALL http
                    val postText = URLEncoder.encode(myView.etPost.text.toString(),"utf-8")
                    DownloadURL= URLEncoder.encode(DownloadURL,"utf-8")
                    val url="http://10.0.2.2/~hussienalrubaye/TwitterAndroidServer/TweetAdd.php?user_id=" + SaveSettings.userID  +"&tweet_text=" + postText +"&tweet_picture="+ DownloadURL
                    MyAsyncTask().execute(url)

                    myView.etPost.setText("")
                })
                return myView
            } else if(mytweet.tweetDate.equals("loading")){
                var myView=layoutInflater.inflate(R.layout.loading_ticket,null)
                return myView
            }else{
                var myView=layoutInflater.inflate(R.layout.tweets_ticket,null)
                myView.txt_tweet.text = mytweet.tweetText
                myView.txt_tweet_date.text=mytweet.tweetDate
                //myView.tweet_picture.setImageURI(mytweet.tweetImageURL)
                Picasso.with(context).load(mytweet.tweetImageURL).into(myView.tweet_picture)

                Picasso.with(context).load(mytweet.personImage).into(myView.picture_path)
                myView.txtUserName.text = mytweet.personName
                myView.txtUserName.setOnClickListener {
                    //
                    val url="http://10.0.2.2/~hussienalrubaye/TwitterAndroidServer/TweetList.php?op=2&user_id=" + mytweet.personID +"&StartFrom=0"
                    MyAsyncTask().execute(url)
                }
                return myView
            }



        }

        override fun getItem(p0: Int): Any {
            return listNotesAdpater[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {

            return listNotesAdpater.size

        }



    }


    //Load image

    val PICK_IMAGE_CODE=123
    fun loadImage(){

        var intent= Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent,PICK_IMAGE_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==PICK_IMAGE_CODE  && data!=null && resultCode == RESULT_OK){

            val selectedImage=data.data
            val filePathColum= arrayOf(MediaStore.Images.Media.DATA)
            val cursor= contentResolver.query(selectedImage!!,filePathColum,null,null,null)
            cursor!!.moveToFirst()
            val coulomIndex=cursor!!.getColumnIndex(filePathColum[0])
            val picturePath=cursor!!.getString(coulomIndex)
            cursor!!.close()
            UploadImage(BitmapFactory.decodeFile(picturePath))
        }

    }

    var DownloadURL:String?="noImage"
    fun UploadImage(bitmap:Bitmap){
        ListTweets.add(0,Ticket("0","him","url","loading","","",""))
        adpater!!.notifyDataSetChanged()

        val storage= FirebaseStorage.getInstance()
        val storgaRef=storage.getReferenceFromUrl("gs://twitterwebservice-b75b6.appspot.com")
        val df= SimpleDateFormat("ddMMyyHHmmss")
        val dataobj= Date()
        val imagePath= SaveSettings.userID + "."+ df.format(dataobj)+ ".jpg"
        val ImageRef=storgaRef.child("imagePost/"+imagePath )
        val baos= ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos)
        val data= baos.toByteArray()
        val uploadTask=ImageRef.putBytes(data)
        uploadTask.addOnFailureListener{
            Toast.makeText(applicationContext,"fail to upload", Toast.LENGTH_LONG).show()
        }.addOnSuccessListener { taskSnapshot ->

            DownloadURL= taskSnapshot.storage.downloadUrl.toString()
            ListTweets.removeAt(0)
            adpater!!.notifyDataSetChanged()

        }
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
                Toast.makeText(applicationContext,json.getString("msg"),Toast.LENGTH_LONG).show()


                if (json.getString("msg")== "tweet is added"){
                    DownloadURL="noImage"
                    ListTweets.removeAt(0)
                    adpater!!.notifyDataSetChanged()
                }else if ( json.getString("msg")=="has tweet"){
                    ListTweets.clear()
                    ListTweets.add(Ticket("0","him","url","add","","",""))


                    // get tweets
                    val tweets = JSONArray(json.getString("info"))
                    for (i in 0..tweets.length()-1){
                        val singleTweet= tweets.getJSONObject(i)
                        ListTweets.add(Ticket(singleTweet.getString("tweet_id"),singleTweet.getString("tweet_text"),
                                singleTweet.getString("tweet_picture"),singleTweet.getString("tweet_date")
                                ,singleTweet.getString("first_name"),singleTweet.getString("picture_path"),
                                singleTweet.getString("user_id")))

                    }
                }else if ( json.getString("msg")=="no tweets"){
                    ListTweets.clear()
                    ListTweets.add(Ticket("0","him","url","add","","",""))

                }



                adpater!!.notifyDataSetChanged()

            }catch (ex:Exception){}
        }

        override fun onPostExecute(result: String?) {

            //after task done
        }


    }




}
