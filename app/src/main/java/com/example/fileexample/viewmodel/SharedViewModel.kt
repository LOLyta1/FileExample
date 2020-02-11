package com.example.fileexample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fileexample.model.VideoFile
import kotlin.collections.ArrayList

class SharedViewModel : ViewModel() {
    var logsArray=ArrayList<String>()
    val logsMutableList = MutableLiveData(logsArray)

    var videosList = ArrayList<VideoFile>()
    var videosMutableList = MutableLiveData(videosList)


    fun addLog(log: String) {
          logsArray.add(log)
          logsMutableList.value=logsArray
    }

    fun addVideo(video:VideoFile){
        videosList.add(video)
        videosMutableList.value=videosList
    }

}