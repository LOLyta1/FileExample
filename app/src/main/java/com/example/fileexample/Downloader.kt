package com.example.fileexample

import okhttp3.*
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit


class Downloader(val listener:Listener, val file : File) {

    interface Listener{
        fun showResponse(response:String?)
    }

    private val url="https://habr.com/ru/company/solarsecurity/blog/427431/"
    private var client=OkHttpClient.Builder().cache(Cache(file,maxSize = Long.MAX_VALUE)).build()
    private var request=Request.Builder().url(url).build()
                        //cache(file).build()).


    fun getResponse(){
        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                listener.showResponse("ОШИБКА загрузки ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                if(response.isSuccessful){
                    listener.showResponse(response.body?.string())
                }else{
                    listener.showResponse("код ошибки - ${response.code}")

                }
            }

        })
    }
}