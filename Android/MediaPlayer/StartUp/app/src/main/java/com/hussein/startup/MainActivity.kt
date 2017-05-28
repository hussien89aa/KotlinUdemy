package com.hussein.startup

import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import  kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.song_ticket.view.*

class MainActivity : AppCompatActivity() {

    var listSongs=ArrayList<SongInfo>()
    var adapter:MySongAdapter?=null
    var mp:MediaPlayer?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // LoadURLOnline()
        CheckUserPermsions()


        var mytracking=mySongTrack()
        mytracking.start()

    }

    fun LoadURLOnline(){
        listSongs.add(SongInfo("001","Ahmed","http://server6.mp3quran.net/thubti/001.mp3"))
        listSongs.add(SongInfo("002","Ahmed","http://server6.mp3quran.net/thubti/002.mp3"))
        listSongs.add(SongInfo("003","Alex","http://server6.mp3quran.net/thubti/003.mp3"))
        listSongs.add(SongInfo("004","Ahmed","http://server6.mp3quran.net/thubti/004.mp3"))
        listSongs.add(SongInfo("005","Alex","http://server6.mp3quran.net/thubti/005.mp3"))
    }

    inner  class MySongAdapter:BaseAdapter{
         var  myListSong=ArrayList<SongInfo>()
        constructor(myListSong:ArrayList<SongInfo>):super(){
            this.myListSong=myListSong
        }
        override fun getView(postion: Int, p1: View?, p2: ViewGroup?): View {
           val myView= layoutInflater.inflate(R.layout.song_ticket,null)
            val Song= this.myListSong[postion]
            myView.tvSongName.text = Song.Title
            myView.tvAuthor.text = Song.AuthorName

            myView.buPlay.setOnClickListener(View.OnClickListener {
                //TODO: play song

                if( myView.buPlay.text.equals("Stop") ){
                    mp!!.stop()
                    myView.buPlay.text = "Start"
                }else {

                    mp = MediaPlayer()
                    try {
                        mp!!.setDataSource(Song.SongURL)
                        mp!!.prepare()
                        mp!!.start()
                        myView.buPlay.text = "Stop"
                        sbProgress.max=mp!!.duration
                    } catch (ex: Exception) {
                    }
                }
            })

            return  myView

        }

        override fun getItem(item: Int): Any {
           return this.myListSong[item]
        }

        override fun getItemId(p0: Int): Long {
            return  p0.toLong()
        }

        override fun getCount(): Int {
            return this.myListSong.size
        }


    }

    inner  class  mySongTrack():Thread(){


          override fun run() {
          while(true){
              try{
                  Thread.sleep(1000)
              }catch (ex:Exception){}

              runOnUiThread {

                  if (mp!=null){
                  sbProgress.progress = mp!!.currentPosition
                 }
              }
          }

        }
    }

      fun CheckUserPermsions() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                        REQUEST_CODE_ASK_PERMISSIONS)
                return
            }
        }

          LoadSong()

    }

    //get acces to location permsion
    private val REQUEST_CODE_ASK_PERMISSIONS = 123


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CODE_ASK_PERMISSIONS -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                LoadSong()
            } else {
                // Permission Denied
                Toast.makeText(this, "denail", Toast.LENGTH_SHORT)
                        .show()
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    fun   LoadSong() {
        val allSongsURI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val selection = MediaStore.Audio.Media.IS_MUSIC + "!=0"
        val cursor = contentResolver.query(allSongsURI, null, selection, null, null)
        if (cursor != null) {
            if (cursor!!.moveToFirst()) {

                do {

                    val songURL = cursor!!.getString(cursor!!.getColumnIndex(MediaStore.Audio.Media.DATA))
                    val SongAuthor = cursor!!.getString(cursor!!.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                    val SongName = cursor!!.getString(cursor!!.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME))
                    listSongs.add(SongInfo(SongName, SongAuthor, songURL))
                } while (cursor!!.moveToNext())


            }
            cursor!!.close()

            adapter=MySongAdapter(listSongs)
            lsListSongs.adapter=adapter
        }
    }

}
