

fun main(args:Array<String>){

    print("enter your grade:")
    var grade= readLine()!!.toDouble()

    if (grade>=90){
        //block of code

        println(" You are in A level")
    }


    if( grade>=50 && grade <=70){
        grade=grade+10
        println(" you get extra 10 point")
    }


    println(" you enter grade $grade")



}