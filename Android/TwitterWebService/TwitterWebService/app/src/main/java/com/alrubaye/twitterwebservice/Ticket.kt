package com.alrubaye.twitterwebservice

/**
 * Created by hussienalrubaye on 8/6/17.
 */
class  Ticket{
    var tweetID:String?=null
    var tweetText:String?=null
    var tweetImageURL:String?=null
    var tweetDate:String?=null
    var personName:String?=null
    var personImage:String?=null
    var personID:String?=null
    constructor(tweetID:String,tweetText:String,tweetImageURL:String,
                tweetDate:String,personName:String,personImage:String,personID:String){
        this.tweetID=tweetID
        this.tweetText=tweetText
        this.tweetImageURL=tweetImageURL
        this.tweetDate=tweetDate
        this.personName=personName
        this.personImage=personImage
        this.personID=personID
    }
}