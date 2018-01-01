import java.util.*

/**
 * Created by hussienalrubaye on 12/31/17.
 */

class Person(var name:String, var age:Int): Comparable<Person>{


    override fun compareTo(other: Person): Int {
         return this.age -  other.age
    }
}



fun  main(args:Array<String>){
    var listOfNames= ArrayList<Person>()
    listOfNames.add(Person("Jena",3))
    listOfNames.add(Person("Laya",1))
    listOfNames.add(Person("Hussein",28))
    println("before sort")
    for (person in listOfNames){
        println("Name:"+person.name)
        println("age:"+person.age)
    }
    println("after sort")
    Collections.sort(listOfNames)
    for (person in listOfNames){
        println("Name:"+person.name)
        println("age:"+person.age)
    }

}