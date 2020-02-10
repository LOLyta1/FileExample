package com.example.fileexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.collections.ArrayList

class SharedViewModel : ViewModel() {
    var array=ArrayList<String>()
    val logsList = MutableLiveData(array)

    fun addItem(item: String) {
          array.add(item)
          logsList.value=array
    }

}