package com.hussein.startup

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.widget.Toast
import  kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity(),SensorEventListener {
    var sensor:Sensor?=null
    var sensorManager:SensorManager?=null
    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
       }


    var xold=0.0
    var yold=0.0
    var zold=0.0
   var threadShould=3000.0
    var oldtime:Long=0
    override fun onSensorChanged(event: SensorEvent?) {
        var x=event!!.values[0]
        var y=event!!.values[1]
        var z=event!!.values[2]
        var currentTime=System.currentTimeMillis()
        if((currentTime-oldtime)>100){
            var timeDiff=currentTime-oldtime
            oldtime=currentTime
            var speed=Math.abs(x+y+z-xold-yold-zold)/timeDiff*10000
            if(speed>threadShould){
                var v=getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                v.vibrate(500)
                Toast.makeText(applicationContext,"shock",Toast.LENGTH_LONG).show()
            }
        }
     }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sensorManager=getSystemService(Context.SENSOR_SERVICE)as SensorManager
        sensor=sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }

}
