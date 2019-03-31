

fun addNumbers(x:Double=0.0,y:Double=0.0):Double{

    return x+y

}


fun displayInfo( vararg  names:String){
    for(name in names){
        println(name)
    }

}


fun main(){
    println(" Start Main Fun")

    var returnAdd= addNumbers( 3.0, 4.0)
    println("returnAdd: $returnAdd")

    returnAdd= addNumbers(x=5.0,y=12.0)
    println("returnAdd: $returnAdd")


    returnAdd =  addNumbers(y=10.0)
    println("returnAdd: $returnAdd")

    returnAdd =  addNumbers(10.0)
    println("returnAdd: $returnAdd")


    displayInfo( names = *arrayOf("Jena","Laya","Dora"))

    println("End Main Fun")
}