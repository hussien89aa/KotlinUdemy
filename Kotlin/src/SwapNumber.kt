

fun main(){

    print("Enter number1: ")
    var number1 = readLine()!!.toInt()

    print("Enter number2: ")
    var number2 = readLine()!!.toInt()

    val temp = number1
    number1 = number2
    number2 = temp

    println("Swap numbers is--> Number1: $number1, Number2: $number2")
}