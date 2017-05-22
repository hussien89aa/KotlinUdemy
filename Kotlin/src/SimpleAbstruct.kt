abstract class CreditCard(){
    fun CreditID():String
    { return "23432dsdfds"}
    abstract fun newCredir()

}

class UserInfo(): CreditCard(){

    fun getInfo():String{
        return CreditID();
    }
    override  fun newCredir(){
        println(" new cart created")
    }
}



fun main(args:Array<String>){

    //var credit=CreditCard()
    //println(credit.CreditID())
    var user=UserInfo()
    println(user.getInfo())
}