



fun main(){

    println("Pets App")
    print("Enter number of Pets: ")
    val maxSize = readLine()!!.toInt()
    //Write App in Array
    var listOfPets:Array<String> = Array(maxSize){""}
    for(i in 0 until  maxSize){
        print("Enter Pet name $i:")
        listOfPets[i] = readLine()!!.toString()
    }

    println("Your pets Are using Array")
    for(i in 0 until maxSize){
        println("Pet $i:  ${listOfPets[i]}")
    }


}