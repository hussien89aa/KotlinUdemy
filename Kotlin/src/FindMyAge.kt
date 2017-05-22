import java.util.*


fun main(args:Array<String>){

    //input
    print("Enter your DOB:")
    var DOB:Int= readLine()!!.toInt()

    //process
    var year=Calendar.getInstance().get(Calendar.YEAR)
    var Age:Int?
    Age=year-DOB

    //output

    println("Your age is $Age years")


}