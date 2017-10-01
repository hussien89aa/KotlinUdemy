package com.alrubaye.notifymeapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color

/**
 * Created by hussienalrubaye on 9/30/17.
 */
class  NotificationHelper(conext:Context): ContextWrapper(conext) {

 val  manager:NotificationManager by lazy{
     getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
 }

    init {
        //first channel
        val chan1= NotificationChannel(FIRST_CHANNEL,"First channel",NotificationManager.IMPORTANCE_DEFAULT)
        chan1.lightColor=Color.GREEN
        chan1.lockscreenVisibility=Notification.VISIBILITY_PRIVATE
        manager.createNotificationChannel(chan1)


        // second channel
        val chan2= NotificationChannel(SECOND_CHANNEL,"Second channel",NotificationManager.IMPORTANCE_DEFAULT)
        chan2.lightColor=Color.GREEN
        chan2.lockscreenVisibility=Notification.VISIBILITY_PRIVATE
        manager.createNotificationChannel(chan2)


        // third channel
        val chan3= NotificationChannel(THIRD_CHANNEL,"Third channel",NotificationManager.IMPORTANCE_DEFAULT)
        chan3.lightColor=Color.GREEN
        chan3.lockscreenVisibility=Notification.VISIBILITY_PRIVATE
        manager.createNotificationChannel(chan3)

    }


    fun  getNotification1(title:String,body:String):Notification.Builder{

        return Notification.Builder(applicationContext,FIRST_CHANNEL)
                .setContentText(body)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.bullbasaur)
                .setAutoCancel(true)
    }

    fun  getNotification2(title:String,body:String):Notification.Builder{

        return Notification.Builder(applicationContext,SECOND_CHANNEL)
                .setContentText(body)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.bullbasaur)
                .setAutoCancel(true)
    }

    fun  getNotification3(title:String,body:String):Notification.Builder{

        return Notification.Builder(applicationContext,THIRD_CHANNEL)
                .setContentText(body)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.bullbasaur)
                .setAutoCancel(true)
    }

    fun Notify(id:Int,notification: Notification.Builder){
        manager.notify(id,notification.build())
    }


    companion object {
        val FIRST_CHANNEL="first"
        val SECOND_CHANNEL="second"
        val THIRD_CHANNEL="third"
    }
}