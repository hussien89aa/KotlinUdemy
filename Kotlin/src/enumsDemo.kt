
enum class Direction{
        NORTH,SOUTH,EAST,WEST
}

fun main(args:Array<String>){

    var userdirect=Direction.SOUTH

    if(userdirect==Direction.NORTH){
        println("he wen to north")
    }else{
        println(" i donot know where he went")
    }

}