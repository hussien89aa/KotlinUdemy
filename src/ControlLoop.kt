
fun main(args:Array<String>){


   loop@ for (count in 1..10){

        for (count2 in 1..5) {
            println("count:$count")
            if(count2==2){
                break@loop
            }
        }

    }

    println(" loop done")

}