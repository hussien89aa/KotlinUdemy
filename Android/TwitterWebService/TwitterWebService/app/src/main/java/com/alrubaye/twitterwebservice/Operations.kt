package com.alrubaye.twitterwebservice

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

/**
 * Created by hussienalrubaye on 8/6/17.
 */
class Operations{

    fun ConvertStreamToString(inputStream: InputStream):String{

        val bufferReader= BufferedReader(InputStreamReader(inputStream))
        var line:String
        var AllString:String=""

        try {
            do{
                line=bufferReader.readLine()
                if(line!=null){
                    AllString+=line
                }
            }while (line!=null)
            inputStream.close()
        }catch (ex:Exception){}



        return AllString
    }

}