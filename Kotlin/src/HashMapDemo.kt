import java.util.*


fun main(){

    var listOfUsers = HashMap<Int,String>()
    listOfUsers[123]= "Hussein"
    listOfUsers[554]= "Jena"
    listOfUsers[12]= "Laya"

    listOfUsers[123]= "Rana"

    for (key in listOfUsers.keys)
    println("$key: ${listOfUsers[key]}")



}