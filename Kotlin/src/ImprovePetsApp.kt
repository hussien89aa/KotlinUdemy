import java.util.*
import kotlin.collections.HashMap


fun main(){

    var listOfUsers = HashMap<String,LinkedList<String>>()
    while (true ){
        print(" Enter your name or quit: ")
        val name = readLine()!!.toString()
        if(name=="quit"){
            break
        }
        print(" Where you live: ")
        val livePlace = readLine()!!.toString()

        var listOfUserPets = LinkedList<String>()
        do{

            print("Enter Pet name or type next: ")
            val petName= readLine()!!.toString()
            if(petName!="next"){
                listOfUserPets.add( petName  )
            }

        }while(petName!="next")

        listOfUsers["$name:$livePlace"]= listOfUserPets

    }


    println("===== User info ====")
    for( key in listOfUsers.keys){

        println("name and place live: $key")
        //println("livePlace: $livePlace")
        val listOfPets= listOfUsers[key]!!
        for (petName in listOfPets){
            println("petName: $petName")
        }


        if (listOfPets.contains("cat")){
            println("We have program for your cat")
        }

    }
}