package com.example.fileexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :
    AppCompatActivity(),
    Downloader.Listener {
    var responseText:String?=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        send_request_button.setOnClickListener {  Downloader(this,cacheDir).getResponse() }
        show_response_button.setOnClickListener {  response_text_view.text=responseText }

      //  Log.d("mylog","путь кэш-директории ${cacheDir.path}")
    }

    override fun showResponse(response: String?) {
        responseText=response

    }

}
