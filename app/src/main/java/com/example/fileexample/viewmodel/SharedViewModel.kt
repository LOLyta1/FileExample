package com.example.fileexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.collections.ArrayList

class SharedViewModel : ViewModel() {
    val logsList = MutableLiveData(ArrayList<String>())

    fun addItem(item: String) {
        this.logsList.value = logsList.value?.plus(item) as ArrayList<String>
    }
}