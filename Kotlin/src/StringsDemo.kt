



fun main(){


    val message= " Welcome to new York?"
    val name = "Hussein"
    val allMessage = "$name,$message"
    println(allMessage[0])
    println(allMessage.toLowerCase())
    println(allMessage.toUpperCase())
    println(message.trim())
    println(message)
    val listOfTokens = message.trim().split(" ")
    for (token in listOfTokens){
        if (!token.contains("to") && !token.contains("is")){
            println("token: $token")
        }

    }


}