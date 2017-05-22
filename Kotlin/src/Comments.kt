
// This main function
fun main(args:Array<String>){
    //Enter user name
    print("Enter name:")
    var name= readLine()
    print("Enter age:")
    var age:Int= readLine()!!.toInt()
    print("Enter department:")
    var dep:String?
    dep= readLine()
    print("Enter pi:")
    var pi:Double= readLine()!!.toDouble()

    /* print output
     to allow users to see the varibles values
     */
    println("**** output *****")
    println("name:"+ name)
    println("age:"+ age)
    println("department:"+ dep)
    println("PI:"+ pi)



}