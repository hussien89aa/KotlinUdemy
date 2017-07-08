package com.hussein.startup

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.BaseAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*
import  kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.contact_ticket.view.*

class MainActivity : AppCompatActivity() {
    var adapter: ContactAdapter?=null
    var listOfContact=ArrayList<UserContact>()
    var databaseRef:DatabaseReference?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userData= UserData(this)
        userData.loadPhoneNumber()

        databaseRef= FirebaseDatabase.getInstance().reference

        // For Deby=ug only
        //dummpyData()

        adapter = ContactAdapter(this, listOfContact)
        lvContactList.adapter= adapter
        lvContactList.onItemClickListener= AdapterView.OnItemClickListener{
            parent,view,postion,id ->
            val userInfo =listOfContact[postion]

        }



    }

    override fun onResume() {
        super.onResume()
        refreshUsers()
    }

    fun refreshUsers(){
        val userData= UserData(this)
         if (userData.loadPhoneNumber()=="empty"){
             return
         }

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
                       listOfContact.add(UserContact("NO_NAME" ,key))

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
}
