package com.example.fileexample.presenter

import android.content.Context
import android.os.Environment
import android.widget.Toast
import com.example.fileexample.model.FileValues
import com.example.fileexample.view.IView
import java.io.File
import java.lang.Exception

class FilePresenter(val iView: IView) {
 private val model = FileValues(name="",content = "", storage = "")

    fun createFileInternal(name: String): File? {
        return try {
            val path =iView.mContext?.filesDir
            val file= File(path, name)
            iView.showInfoMessage("создан файл: $name в директории $path")
            file
        } catch (ex: Exception) {
            iView.showError(ex)
            null
        }
    }

    fun createFileExternal(name: String, type: String?): File? {
       //допиасать проверку на состояние хранилища - sdcard извлечена/нет   return File(context?.filesDir, name)
        return null
    }


    fun saveToFile(name: String, content: String) {
        try {
            iView?.mContext?.openFileOutput(name, Context.MODE_PRIVATE).use {
                it?.write(content.toByteArray())
            }
        } catch (e: Exception) {
            iView.showError(e)
        }

    }

    fun readFile(name: String) {
        model.name=name
        try {
            val bufferRead = iView.mContext?.openFileInput(name)?.bufferedReader()
                bufferRead?.forEachLine {
                    model.content += "\n" + it
                }

            iView.showFileText("имя файла: - ${model.name}, текст файла: ${model.content} " )
        } catch (ex: Exception) {
            iView.showError(ex)
        }
    }

}


