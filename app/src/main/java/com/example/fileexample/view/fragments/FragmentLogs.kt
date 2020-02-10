package com.example.fileexample.view.fragments

import android.content.res.Resources
import android.graphics.BlendMode
import android.graphics.Canvas
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.util.TypedValue.COMPLEX_UNIT_DIP
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fileexample.R
import com.example.fileexample.view.recyclers.LogAdapter
import com.example.fileexample.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_logs.view.*
import java.util.*
import kotlin.collections.ArrayList

class FragmentLogs : Fragment() {

    private var viewModel: SharedViewModel? = null

    private val observer = object:Observer<ArrayList<String>>{
        override fun onChanged(t: ArrayList<String>?) {
            val adapter = view?.logs_recycler_view?.adapter as LogAdapter
            t?.let {
                adapter.setLogsList(it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.let { ViewModelProviders.of(it).get(SharedViewModel::class.java) }
        viewModel?.logsList?.observe(this, observer)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_logs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
    }

    private fun setupRecycler() {
        if (view != null) {
            view!!.logs_recycler_view.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = LogAdapter()
            }

            view!!.logs_recycler_view?.addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    super.getItemOffsets(outRect, view, parent, state)
                    outRect.left = pxToDp(5)
                    outRect.right = pxToDp(5)
                    outRect.top = pxToDp(5)
                    outRect.bottom = pxToDp(5)
                }

                override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                    super.onDraw(c, parent, state)
                    c.drawColor(resources.getColor(R.color.colorPrimaryLight, null))
                }
            })
        }
    }
    private fun pxToDp(value: Int): Int {
        return TypedValue.applyDimension(
            COMPLEX_UNIT_DIP,
            value.toFloat(),
            Resources.getSystem().displayMetrics
        ).toInt()
    }
}