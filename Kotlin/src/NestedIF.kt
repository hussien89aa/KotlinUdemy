


fun main(){


    print("Enter your grade: ")
    val grade = readLine()!!.toInt()


    if(grade>= 90){
        if( grade> 93){
            println("A+")
        }else{
            println("A-")
        }


    } else if(grade in 80..89){
        if(grade>85){
            println("B+")
        }else{
            println("B-")
        }
    }else if(grade in 70..79){
        println("C")
    }else {
        println("Fail")
    }


    println("End of App")


}