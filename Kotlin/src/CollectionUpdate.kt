
/*
mutable: you can update, add more
im-mutable : you cannot update, you cannot add more
 */

fun main(){

    //1- list

    //im-mutable
    val listImmutable = listOf("Jena","laya")
    //list[0]="Hussein"
    for (name in listImmutable){
        println(name)
    }

    //mutable
    var listMutable = mutableListOf("Jena","laya")
    listMutable[0]="Hussein"
    for (name in listMutable){
        println(name)
    }


    val listOfUsersI = mapOf(1 to "hussein", 2 to "jena")

    var listOfUsersM = mutableMapOf(1 to "hussein", 2 to "jena")







}