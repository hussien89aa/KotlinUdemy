
fun main(args:Array<String>){

    var map= hashMapOf(1 to "hussein", 2 to "jena")
    map.put(3,"laya")
    println(map.get(3))
    println(map[3])

    var ar= arrayOf(1,10,22,11)
    println(ar[0])
    var list = mutableListOf(11,22,33,22)
    list[0]=22
    for (item in list){
        println(item)
    }



}