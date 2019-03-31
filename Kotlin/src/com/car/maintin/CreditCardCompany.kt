package com.car.maintin

interface CreditCard{
    val cardNumber:String
    fun score(age:Int)
}


class MasterCard(override val cardNumber: String) :CreditCard{

    override fun score(age: Int) {

        if(age> 50){
            println("Negative")
        }else{
            println("Positive")
        }
      }

    fun print(){
        println("hello")
    }

}


class Visa(override val cardNumber: String) :CreditCard{

    override fun score(age: Int) {

        if(age> 60){
            println("Negative")
        }else{
            println("Positive")
        }
    }

}
class PayPal(client:CreditCard): CreditCard by client{

    fun print(){
        println(this.cardNumber)
    }
}

fun main() {
    val visa =Visa("74262374238462")
    visa.score(60)

    val payPal1 = PayPal(visa)
    println("cardNumber PayPal: ${payPal1.cardNumber}")
    payPal1.print()

    val masterCard =  MasterCard("847563487435734")
    masterCard.score(60)

    val payPal2 = PayPal(masterCard)
    println("cardNumber PayPal: ${payPal2.cardNumber}")
    payPal2.print()

 

}














