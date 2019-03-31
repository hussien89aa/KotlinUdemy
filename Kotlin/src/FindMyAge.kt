import java.util.*

fun main(){

    print("Enter Year of Birth: ")
    val yearOfBirth = readLine()!!.toInt()
    val yearInDevice = Calendar.getInstance().get(Calendar.YEAR)
    val age = yearInDevice - yearOfBirth

    print("You are $age years old")

}