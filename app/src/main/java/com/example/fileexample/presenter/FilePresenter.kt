package com.example.fileexample.presenter

import android.content.Context
import android.os.Environment
import com.example.fileexample.StorageTypes
import com.example.fileexample.model.FileValues
import com.example.fileexample.view.IView
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.FileReader
import java.lang.Exception
import java.nio.file.Files
import java.text.DateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class FilePresenter(val iView: IView) {

    private var model = FileValues(name = "", content = "", storage = "")


fun createFile(name: String, content: String,storageType: StorageTypes) {
    try {
        val path=getStoragePath(storageType)
        if (path!= null) {
            val streamer=FileOutputStream(File("${path}/${name}"))
            streamer.write(content.toByteArray())
            streamer.close()
            iView.showLog("создан файл: $name в директории ${path}")
        }
    } catch (ex: Exception) {
        iView.showError(ex)
    }
}

fun readFile(name: String, storageType: StorageTypes) {
    try {
        val path = getStoragePath(storageType)
        if (path != null) {
            val reader=FileInputStream("${path}/$name").reader()
            val text=reader.readText()
            reader.close()
            iView.showFileText("имя файла: - ${name}, текст файла: ${text} ")
        }
    } catch (ex: Exception) {
        iView.showError(ex)
    } finally {
        model = FileValues(name = "", content = "", storage = "")
    }
}

    private fun getStoragePath(storageType: StorageTypes):String?{
        return when(storageType){
                StorageTypes.INTERNAL -> iView.mContext?.filesDir?.path
                StorageTypes.EXTERNAL_MEDIA -> iView.mContext?.getExternalFilesDir(null)?.path
                StorageTypes.INTERNAL_CACHE-> iView.mContext?.cacheDir?.path
                StorageTypes.EXTERNAL_CACHE-> iView.mContext?.externalCacheDir?.path
        }
   }

}


