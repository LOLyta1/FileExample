package com.example.fileexample.presenter

import android.content.Context
import android.os.Environment
import com.example.fileexample.StorageTypes
import com.example.fileexample.model.FileValues
import com.example.fileexample.view.IView
import java.io.File
import java.lang.Exception

class FilePresenter(val iView: IView) {

    private var model = FileValues(name = "", content = "", storage = "")

    fun createFile(name: String, storageType: StorageTypes, type: String?=null): File? {
        var file: File? = null
        try {
            when (storageType) {
                StorageTypes.INTERNAL ->  file = iView.mContext?.filesDir
                StorageTypes.EXTERNAL_MEDIA -> file = iView.mContext?.getExternalFilesDir(type)
                StorageTypes.INTERNAL_CACHE->file = iView.mContext?.cacheDir
                StorageTypes.EXTERNAL_CACHE->file = iView.mContext?.externalCacheDir
            }
            iView.showLog("создан файл: $name в директории ${file?.absolutePath}")
        } catch (ex: Exception) {
            iView.showError(ex)
        }
        return file
    }





fun saveToFile(name: String, content: String) {
    try {
        iView.mContext?.openFileOutput(name, Context.MODE_PRIVATE).use {
            it?.write(content.toByteArray())
        }
    } catch (e: Exception) {
        iView.showError(e)
    }

}



fun readFile(name: String) {
    model.name = name
    try {
        val bufferRead = iView.mContext?.openFileInput(name)?.bufferedReader()
        bufferRead?.forEachLine {
            model.content += "\n" + it
        }
        iView.showFileText("имя файла: - ${model.name}, текст файла: ${model.content} ")
    } catch (ex: Exception) {
        iView.showError(ex)
    }finally {
        model=FileValues(name = "", content = "", storage = "")
    }
}



}


