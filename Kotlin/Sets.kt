
fun main(args:Array<String>){

    var setEmenet= setOf(1,2,3,11,44,55,55)
    //setEmenet.add(77)  not work
    for (element in setEmenet){
        println(element)
    }

    var setEmenetM= mutableSetOf(1,2,3,11,44,55,55)
    setEmenetM.add(77)
    for (element in setEmenetM){
        println(element)
    }

}