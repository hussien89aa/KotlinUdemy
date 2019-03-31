/*
Example of Extensions in Kotlin
 */
package com.car.maintin


fun ArrayList<Int>.findMax():Int{
  var maxNumber =0
    for(item in this){
      if(item > maxNumber){
          maxNumber =item
      }
    }
    return maxNumber
}
fun main() {

     val listOfElements = ArrayList<Int>()
    listOfElements.add(12)
    listOfElements.add(1)
    listOfElements.add(4)
    listOfElements.add(5)
    for(item in listOfElements){
        println(item)
    }

    println("Max: ${listOfElements.findMax()}")
}