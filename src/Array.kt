
fun main(args:Array<String>){

    var arrayInt= Array<Int>(5){0}
    arrayInt[3]=55
    println("Array[3]="+ arrayInt[3])

    println("Al element by object")
    for (element in arrayInt){
        println(element)
    }
    println("Al element by index")
    for( index in 0..4){
        println(arrayInt[index])
    }

    var arrayStr=Array<String>(4){""}
    for (index in 0..3){
        print("arrayStr[ $index ]=")
        arrayStr[index]= readLine()!!
    }

    for (index in 0..3){
        println("arrayStr[ $index ]="+ arrayStr[index])
    }

}