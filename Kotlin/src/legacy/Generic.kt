package legacy
class  UserAdmins<T >(credit:T ){

    init {
        println(credit)
    }

}

fun <T> display(process:T){
    println(process)
}

fun main(args:Array<String>){

    var userAdmin =UserAdmins("hussein")
    var userAdmin2 =UserAdmins(1234)
    var userAdmin3 =UserAdmins(1234.5)

    display(22)
    display<String>("process user")
}