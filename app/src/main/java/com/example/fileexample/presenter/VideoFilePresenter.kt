package com.example.fileexample.presenter

import android.database.Cursor
import android.provider.MediaStore
import com.example.fileexample.model.VideoFile
import com.example.fileexample.view.interfaces.IVideoView

class VideoFilePresenter(val iVideoView: IVideoView) {

    fun getVideoList() {
        val videoList = ArrayList<VideoFile>()
        var uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI

        /*берем именя колонок в БД у ContentProvider-а*/
        val _id = MediaStore.Video.Media._ID
        val name = MediaStore.Video.Media.DISPLAY_NAME
        val size = MediaStore.Video.Media.SIZE
        val duration = MediaStore.Video.Media.DURATION

        val params = arrayOf(_id, name, size, duration)

        val cursor: Cursor? = iVideoView.mContext?.contentResolver?.query(uri,params,null,null,"$name ASC")

        while (cursor?.moveToNext() != null) {
            val videoFile = VideoFile(
                uri=uri,
                name=cursor.getString(cursor.getColumnIndex(name)),
                duration = cursor.getInt(cursor.getColumnIndex(size)),
                size = cursor.getInt(cursor.getColumnIndex(duration))
            )
            videoList.add(videoFile)
        }

        cursor?.close()

        iVideoView.showVideoList()
    }





}