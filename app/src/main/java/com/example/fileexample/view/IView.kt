package com.example.fileexample.view

import android.content.Context
import java.lang.Exception

interface IView {
    val mContext : Context?
    fun showFileText(fileText : String)
    fun showError(ex:Exception)
    fun showLog(message:String)

}