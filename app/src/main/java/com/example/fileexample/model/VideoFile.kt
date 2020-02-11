package com.example.fileexample.model

import android.net.Uri

class VideoFile(var uri: Uri,
                val name: String,
                val duration: Int,
                val size: Int) {

}