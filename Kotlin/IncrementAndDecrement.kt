
/*
Operations rules| Priorites rules
1- ()
2- ++, -- (before Var)
3- ^
4- *, /
5- +, -
6- =
7- ++,-- (After Var)
 */

fun main(args:Array<String>){

    var x=10
    var y=11
    var z= --x+y   // (++x)=(x=x+1) ,(--x)=(x=x-1)/
    println("z:$z")

    println("before x:$x")
    //x=9, y=11
    var m=x+++y
    println("m:$m")
    println("After x:$x")





}