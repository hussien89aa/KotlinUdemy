package com.car.maintin

 class Car(var type:String, val model:Int, val price:Double,
               val milesDrive:Int, val owner:String){

    init {
     println("Object class is created")
    }

    fun getCarPrice():Double{

        return this.price- (this.milesDrive.toDouble()*10)
    }



}


fun main() {

    /*
    val car1 = Car("BMW",2015,10000.0,105,"Hussein Alrubaye")
    car1.type="KIA"
    println(car1.type)
    println(car1.owner)
    println(car1.getCarPrice())

    val car2 = Car("Toyota",2019,39000.0,10,"Jena Alrubaye")
    println(car2.type)
    println(car2.owner)
    println(car2.getCarPrice())
    */

    val listOfCar = arrayListOf<Car>()
    listOfCar.add(Car("BMW",2015,10000.0,105,"Hussein Alrubaye"))
    listOfCar.add(Car("Toyota",2019,39000.0,10,"Jena Alrubaye"))

    for(car in listOfCar){
        println("----------")
        println(car.owner)
        println(car.getCarPrice())
    }


}