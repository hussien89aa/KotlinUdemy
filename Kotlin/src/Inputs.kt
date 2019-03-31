/*
This App get user info then print them
 We did it as part from learning session
*/

fun main(){


    //Ask for user name
    print("Enter Name: ")
    val name    = readLine()!!.toString()
    //Ask For Age
    print("Enter Age: ")
    val age  = readLine()!!.toInt()
    print("Enter GPA: ")
    val GPA  =  readLine()!!.toDouble()

    println("===== User info ===== ")
    println("Your Name is : $name")
    println("Your Age: $age"   )
    println("Your GPA: $GPA" )




}