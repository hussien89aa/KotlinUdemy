import java.util.*


fun main(){

    println("Pets App")

    //Write App in Array
    var listOfPets= LinkedList<String>()

    do{
        print("Enter Pet name  or exit to quit:")
        val petName =readLine()!!.toString()
        if(petName != "quit"){
            listOfPets.add(petName)
        }

    }while (petName!="quit")

    println("Your pets Are using Index")
    for(i in 0 until listOfPets.size){
        println("Pet $i:  ${listOfPets[i]}")
    }

    println("Your pets Are using Object")
    for(pet in  listOfPets ){
        println("Pet:  $pet")
    }

    if (listOfPets.contains("cat")){
        println("You cat qualified for special care")
    }


}