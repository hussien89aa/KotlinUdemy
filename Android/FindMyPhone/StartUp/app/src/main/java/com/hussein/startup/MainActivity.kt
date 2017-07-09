package com.hussein.startup

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.ActivityCompat
import android.view.*
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*
import  kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.contact_ticket.view.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    var adapter: ContactAdapter?=null
    var listOfContact=ArrayList<UserContact>()
    var databaseRef:DatabaseReference?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userData= UserData(this)
        userData.isFirstTimeLoad()

        databaseRef= FirebaseDatabase.getInstance().reference

        // For Deby=ug only
        //dummpyData()

        adapter = ContactAdapter(this, listOfContact)
        lvContactList.adapter= adapter
        lvContactList.onItemClickListener= AdapterView.OnItemClickListener{
            parent,view,postion,id ->
            val userInfo =listOfContact[postion]
          // get datatime
            val df =SimpleDateFormat("yyyy/MMM/dd HH:MM:ss")
            val date =Date()
            // save to database
            databaseRef!!.child("Users").child(userInfo.phoneNumber).child("request").setValue(df.format(date).toString())

            val intent =Intent(applicationContext,MapsActivity::class.java)
            intent.putExtra("phoneNumber",userInfo.phoneNumber)
            startActivity(intent)
        }



    }

    var IsAccesLocation=false
    override fun onResume() {
        super.onResume()

        val userData= UserData(this)
        if (userData.loadPhoneNumber()=="empty"){
            return
        }
        refreshUsers()

        if (IsAccesLocation) return
        checkContactPermission()
        checkLocationPermission()

    }

    fun refreshUsers(){
         val userData= UserData(this)
        databaseRef!!.child("Users").child(userData.loadPhoneNumber()).child("Finders").addValueEventListener(object :
        ValueEventListener{

            override fun onDataChange(dataSnapshot: DataSnapshot?) {
               try {
                   val td = dataSnapshot!!.value as HashMap<String,Any>

                   listOfContact.clear()

                   if (td==null){
                       listOfContact.add(UserContact("NO_USERS","nothing"))
                       adapter!!.notifyDataSetChanged()
                       return
                   }

                   for (key in td.keys){
                       val name = listOfContacts[key]
                       listOfContact.add(UserContact(name.toString() ,key))

                   }

                   adapter!!.notifyDataSetChanged()
               }catch (ex:Exception){
                   listOfContact.clear()
                   listOfContact.add(UserContact("NO_USERS","nothing"))
                   adapter!!.notifyDataSetChanged()
                   return
               }
            }

            override fun onCancelled(p0: DatabaseError?) {

            }
        })
    }
    //for debug first time
    fun dummpyData(){
        listOfContact.add(UserContact("hussein","3434"))
        listOfContact.add(UserContact("jena","344343"))
        listOfContact.add(UserContact("laya","434543"))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater=menuInflater
        inflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.addTracker ->{
                val intent= Intent(this,MyTrackers::class.java)
                startActivity(intent)
            }
            R.id.help ->{
                //TODO:: as k for help from friend
            }
            else ->{
                return super.onOptionsItemSelected(item)
            }
        }

        return true
    }



    class ContactAdapter: BaseAdapter {
        var listOfContact=ArrayList<UserContact>()
        var context: Context?=null
        constructor(context: Context, listOfContact:ArrayList<UserContact>){
            this.context=context
            this.listOfContact=listOfContact
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val userContact = listOfContact[p0]

            if (userContact.name.equals("NO_USERS")){
                val inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val contactTicketView = inflator.inflate(R.layout.no_user, null)
                return contactTicketView
            }else {
                val inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val contactTicketView = inflator.inflate(R.layout.contact_ticket, null)
                contactTicketView.tvName.text = userContact.name
                contactTicketView.tvPhoneNumber.text = userContact.phoneNumber

                return contactTicketView
            }
        }

        override fun getItem(p0: Int): Any {

            return listOfContact[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {

            return listOfContact.size
        }

    }

    val CONTACT_CODE =123
    fun checkContactPermission(){

        if(Build.VERSION.SDK_INT>=23){

            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS) !=
                    PackageManager.PERMISSION_GRANTED ){

                requestPermissions(arrayOf(android.Manifest.permission.READ_CONTACTS), CONTACT_CODE)
                return
            }
        }
        loadContact()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        when (requestCode) {
            CONTACT_CODE-> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    loadContact()
                } else {
                    Toast.makeText(this, "Cannot acces to contact ", Toast.LENGTH_LONG).show()
                }
            }
            LOCATION_CODE->{
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getUserLocation()
                } else {
                    Toast.makeText(this, "Cannot acces to contact ", Toast.LENGTH_LONG).show()
                }
            }
            else ->{
                super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            }

        }


    }

    var listOfContacts=HashMap<String,String>()
    fun loadContact() {

        listOfContacts.clear()

        val cursor=contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null)
        cursor.moveToFirst()
        do {
            val name=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val phoneNumber=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

            listOfContacts.put(UserData.formatPhoneNumber(phoneNumber),name)
        }while (cursor.moveToNext())
    }



    val LOCATION_CODE =124
    fun checkLocationPermission(){

        if(Build.VERSION.SDK_INT>=23){

            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED ){

                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_CODE)
                return
            }
        }
        getUserLocation()
    }

    fun getUserLocation(){

        var myLocation= MyLocationListener()
        val locationManager=getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3,3f,myLocation)

  //listsent to request
      var userData= UserData(this)
        val myPhoneNumber=userData.loadPhoneNumber()
        databaseRef!!.child("Users").child(myPhoneNumber).child("request").addValueEventListener(
                object :ValueEventListener {
                    override fun onDataChange(p0: DataSnapshot?) {

                        if (MainActivity.myLocation==null) return
                        // get datatime
                        val df = SimpleDateFormat("yyyy/MMM/dd HH:MM:ss")
                        val date = Date()
                        databaseRef!!.child("Users").child(myPhoneNumber)
                                .child("location").child("lat").setValue(MainActivity.myLocation!!.latitude)
                        databaseRef!!.child("Users").child(myPhoneNumber)
                                .child("location").child("log").setValue( MainActivity.myLocation!!.longitude)
                        databaseRef!!.child("Users").child(myPhoneNumber)
                                .child("location").child("lastOnline").setValue( df.format(date).toString())
                    }

                    override fun onCancelled(p0: DatabaseError?) {

                    }
                }
        )

    }
    companion object{
        var myLocation:Location?=null
    }

    inner class MyLocationListener:LocationListener {
        constructor():super(){
            IsAccesLocation=true
            myLocation= Location("me")
            myLocation!!.longitude =0.0
            myLocation!!.latitude=0.0
        }

        override fun onLocationChanged(location: Location?) {
            myLocation=location
         }

        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
          }

        override fun onProviderEnabled(p0: String?) {
         }

        override fun onProviderDisabled(p0: String?) {
         }

    }
}
