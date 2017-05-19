
fun main(args:Array<String>){
    print("Enter name:")
    var name= readLine()
    print("Enter age:")
    var age:Int= readLine()!!.toInt()
    print("Enter department:")
    var dep:String?
    dep= readLine()
    print("Enter pi:")
    var pi:Double= readLine()!!.toDouble()

    println("**** output *****")
    println("name:"+ name)
    println("age:"+ age)
    println("department:"+ dep)
    println("PI:"+ pi)



}