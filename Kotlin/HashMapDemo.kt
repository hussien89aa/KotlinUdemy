
fun main(args:Array<String>){

    var map=HashMap<Int,String>()
    map.put(1,"hussein")
    map.put(2,"Jena")
    map.put(33,"Laya")
    println(map.get(33))

    map.put(33,"Laya ahmed")
    for (k in map.keys){
        println(map.get(k))
    }

}