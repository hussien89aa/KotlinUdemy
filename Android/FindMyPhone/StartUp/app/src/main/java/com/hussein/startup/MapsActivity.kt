package com.hussein.startup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    var dDatabaseRef:DatabaseReference?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val bundle:Bundle= intent.extras!!
        val phoneNumber =bundle.getString("phoneNumber")
        dDatabaseRef=FirebaseDatabase.getInstance().reference

        dDatabaseRef!!.child("Users").child(phoneNumber!!)
                .child("location").addValueEventListener(object:ValueEventListener{

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                try{
                    val td= dataSnapshot!!.value as HashMap<String,Any>
                    val lat =td["lat"].toString()
                    val log =td["log"].toString()
                    MapsActivity.sydney= LatLng(lat.toDouble(),log.toDouble())
                    MapsActivity.lastOnline= td["lastOnline"].toString()
                    loadMap()
                }catch (ex:Exception){}
            }
            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }


    fun loadMap(){
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    companion object{
        var sydney = LatLng(-34.0, 151.0)
        var lastOnline="not defined"
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera

        mMap.addMarker(MarkerOptions().position(sydney).title(lastOnline))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15f))
    }
}
