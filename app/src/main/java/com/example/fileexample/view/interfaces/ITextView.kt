package com.example.fileexample.view.interfaces

import android.content.Context
import java.lang.Exception

interface ITextView {
    val mContext : Context?
    fun showFileText(fileText : String)
    fun showError(ex:Exception)
    fun showLog(message:String)

}