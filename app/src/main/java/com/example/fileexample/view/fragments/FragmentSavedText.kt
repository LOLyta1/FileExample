package com.example.fileexample.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fileexample.R
import com.example.fileexample.view.recyclers.LogAdapter
import com.example.fileexample.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_app_specific_file_text.view.*
import kotlinx.android.synthetic.main.fragment_logs.view.*


class FragmentSavedText :Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_app_specific_file_text,container,false)
    }


}