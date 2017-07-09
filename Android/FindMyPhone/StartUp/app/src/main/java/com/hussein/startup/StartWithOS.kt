package com.hussein.startup

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * Created by hussienalrubaye on 7/9/17.
 */
class  StartWithOS:BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent!!.action.equals("android.intent.action.BOOT_COMPLETED")){
            val intent= Intent(context,MyService::class.java)
            context!!.startService(intent)
        }
    }

}