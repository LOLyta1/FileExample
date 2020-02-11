package com.example.fileexample.view.interfaces

import android.content.Context
import com.example.fileexample.model.VideoFile

interface IVideoView {
    val mContext: Context?
    fun showVideoList()
    fun showVideoFile(video:VideoFile)
    fun showLog(log:String)
    fun showError(message:String)

}