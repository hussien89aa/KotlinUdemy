
interface op{
    fun sum(n1:Int,n2:Int) { println("sum:"+ n1+n2)}
    fun div(n1:Int,n2:Int) { println("div:"+ n1/n2)}
}


class UserOp:op{
    override fun sum(n1: Int, n2: Int) {
        println("sum:"+ n1+n2)
      }

    override fun div(n1: Int, n2: Int) {
        println("div:"+ n1/n2)
    }

}

class AdminOp:op{
    override fun sum(n1: Int, n2: Int) {
        println("sum:"+ (n1+n2-2))
    }

    override fun div(n1: Int, n2: Int) {
        println("div:"+ (n1/n2-2))
    }
    fun display(){
        println(" show")
    }

}

class ManagerOp:op{


}

fun main( args:Array<String>){
    //TODO:
    var adminop=AdminOp()
    adminop.sum(3,10)

}