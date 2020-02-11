package com.example.fileexample.presenter

import com.example.fileexample.StorageTypes
import com.example.fileexample.model.TextFile
import com.example.fileexample.view.interfaces.ITextView
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.lang.Exception

class TextFilePresenter(val iTextView: ITextView) {

    private var model = TextFile(name = "", content = "", storage = "")


fun createFile(name: String, content: String,storageType: StorageTypes) {
    try {
        val path=getStoragePath(storageType,iTextView)
        if (path!= null) {
            val streamer=FileOutputStream(File("${path}/${name}"))
            streamer.write(content.toByteArray())
            streamer.close()
            iTextView.showLog("создан файл: $name в директории ${path}")
        }
    } catch (ex: Exception) {
        iTextView.showError(ex)
    }
}

fun readFile(name: String, storageType: StorageTypes) {
    try {
        val path = getStoragePath(storageType,iTextView)
        if (path != null) {
            val reader=FileInputStream("${path}/$name").reader()
            val text=reader.readText()
            reader.close()
            iTextView.showFileText("Загружено из ${path}/${name}\nTекст файла: ${text} ")
        }
    } catch (ex: Exception) {
        iTextView.showError(ex)
    } finally {
        model = TextFile(name = "", content = "", storage = "")
    }
}
    fun getStoragePath(storageType: StorageTypes, iTextView: ITextView):String?{
        return when(storageType){
            StorageTypes.INTERNAL -> iTextView.mContext?.filesDir?.path
            StorageTypes.EXTERNAL -> iTextView.mContext?.getExternalFilesDir(null)?.path
            StorageTypes.INTERNAL_CACHE-> iTextView.mContext?.cacheDir?.path
            StorageTypes.EXTERNAL_CACHE-> iTextView.mContext?.externalCacheDir?.path
            else->null
        }
    }


}


