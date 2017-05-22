

fun main(args:Array<String>){

    try {
        print("enter N2:")
        var n2:Int= readLine()!!.toInt()
        var Div=100/n2
        println("DIV:$Div")
    }catch (ex:Exception){
        println(ex.message)
    }


    println("app is done")
}