

fun main(args:Array<String>){

    print("enter your grade:")
    var grade= readLine()!!.toDouble()

    if (grade>=90){
        //block of code

        if(grade <93) {
            println(" You are in A- level")
        }else{
            println(" You are in A+ level")
        }
    }


    println(" you enter grade $grade")



}