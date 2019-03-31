


fun  main(){

    print("Pick food menu: ")
    val foodID= readLine()!!.toInt()

    when(foodID){

        1 -> {
            print("You got Sandwich")
            print("You got Salat")
        }

        10 ->{
            println(" You got burger")
        }

        else ->{
            println("You did not order anything")
        }

    }

}