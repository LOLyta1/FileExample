package com.example.fileexample.presenter

import android.content.Context
import android.os.Environment
import android.widget.Toast
import java.io.File
import java.lang.Exception

class FileLib(val context: Context?) {
    companion object {
        val INTERNAL = 1
        val EXTERNAL = 2
    }



    fun createFile(storageType: Int, name: String, type: String?): File? {
        return when (storageType) {
            INTERNAL -> File(context?.filesDir, name)
            EXTERNAL -> {
                if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
                    File(context?.getExternalFilesDir(type), name)
                } else
                    null
            }
            else -> null
        }
    }

    fun saveToFile(name: String, content: String) {
        try {
            context?.openFileOutput(name, Context.MODE_PRIVATE).use {
                it?.write(content.toByteArray())
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Ошибка сохранения", Toast.LENGTH_LONG).show()
        }

    }

    fun readFile(name: String): String? {
        return try {
            File(name)
            context?.openFileInput(name)?.bufferedReader()?.useLines { lines ->
                lines.fold("") { some, text ->
                    "$some\n$text"
                }
            }
        } catch (ex: Exception) {
            null
        }
    }
}