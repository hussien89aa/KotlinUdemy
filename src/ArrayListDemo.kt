
fun main(args:Array<String>){

    var arraylist= ArrayList<String>()
    arraylist.add("jena")
    arraylist.add("Laya")
    arraylist.add("Hussein")
    arraylist.add("Ahmed")

    println("First name:"+ arraylist.get(0))
    arraylist.set(0," Laya Hussein")

    println(" all element by object")
    for ( item in arraylist){
        println(item)
    }

    println(" all element by index")
   for( index in 0..arraylist.size-1){
       println(arraylist.get(index))
   }

    // Search
    if( arraylist.contains("Hussein")){
        println(" name is found")
    }else{
        println(" name is not found")
    }




}