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
import kotlinx.android.synthetic.main.fragment_logs.view.*

class FragmentLogs: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_logs,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        val viewModel=activity?.let{ ViewModelProviders.of(it).get(SharedViewModel::class.java)}
        viewModel?.logsList?.observe(this, Observer<ArrayList<String>> {
            (view.logs_recycler_view.adapter as LogAdapter).apply {
                setLogsList(it)
            }
        })


    }

    private fun setupRecycler() {
        if (view != null) {
            view!!.logs_recycler_view.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = LogAdapter()
            }

            view!!.logs_recycler_view?.addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL).apply {
                    setDrawable(resources.getDrawable(R.drawable.my_devider,null))
                }
            )

        }
    }
}