



fun main(){

    println("Continue Example")
    for (number in 1..10){
        if(number==5){
            continue
        }
        println(number)
    }


    println("Break Example")
    for (number in 1..10){
        if(number==6){
            break
        }
        println(number)
    }


    println("Break Inner Example")
    loop@ for (number in 1..10){
        println("number: $number")
        for (innerLoop in 1..7){
            println("innerLoop: $innerLoop")
            if(innerLoop==6){
                break@loop
            }
        }


    }




    println("End App")
}