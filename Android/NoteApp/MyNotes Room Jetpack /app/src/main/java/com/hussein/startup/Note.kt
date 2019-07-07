package com.hussein.startup

class Note{

    var ID:Int?=null
    var nodeName:String?=null
    var nodeDes:String?=null

    constructor(ID:Int,nodeName:String,nodeDes:String){
        this.ID=ID
        this.nodeName=nodeName
        this.nodeDes=nodeDes
    }
    constructor(nodeName:String,nodeDes:String){

        this.nodeName=nodeName
        this.nodeDes=nodeDes
    }

}
