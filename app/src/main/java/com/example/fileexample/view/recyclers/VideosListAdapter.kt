package com.example.fileexample.view.recyclers

import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fileexample.R
import com.example.fileexample.model.VideoFile

class VideosListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var videosList = ArrayList<VideoFile>()

    class VideosViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
         //val
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return VideosViewHolder(
            LayoutInflater.from(parent.context).inflate(
                android.R.layout.simple_list_item_1,
                parent
            )
        )
    }

    override fun getItemCount(): Int {
        return videosList.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }


}