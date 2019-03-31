
fun main(){

    println("Simple Calculator")
    print("Enter number1: ")
    val number1 = readLine()!!.toDouble()
    print("Enter number1: ")
    val number2  = readLine()!!.toDouble()

    val sum = number1 + number2
    println("sum: $sum")

    val sub =  number1 - number2
    println("sub: $sub")


    val mul = number1 * number2
    println("mul: $mul")

    val div = number1 / number2
    println("div: $div")

}