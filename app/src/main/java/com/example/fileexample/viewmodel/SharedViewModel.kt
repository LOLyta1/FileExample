package com.example.fileexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.collections.ArrayList

class SharedViewModel: ViewModel() {
    var logsList=MutableLiveData<ArrayList<String>>()
    init {
        logsList.value= ArrayList<String>()
    }
}