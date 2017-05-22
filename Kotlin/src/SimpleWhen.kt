
fun main(args:Array<String>){

    var x=30

    when(x) {

        1,2 -> {
            print(" value is 1 or 2")
        }
        in 10..30 -> {
            print(" value in range 10..30")
        }
        !in 10..30 -> {
            print(" value is not in range 10..30")
        }
        else -> {
            print(" value is  out of range")
         }

    }


}