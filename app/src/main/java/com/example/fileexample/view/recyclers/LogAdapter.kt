package com.example.fileexample.view.recyclers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fileexample.R
import kotlinx.android.synthetic.main.item_log.view.*

class LogAdapter : RecyclerView.Adapter<LogAdapter.LogViewHolder>() {
    private var logs = ArrayList<String>()


    class LogViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textView = view.log_text_view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
        return LogViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_log, parent, false)
        )
    }
    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        holder.textView.text = logs[position]
    }

    override fun getItemCount() = logs.size

    fun addItem(log: String) {
        logs.add(log)
        notifyDataSetChanged()
    }

    fun setLogsList(list: ArrayList<String>) {
        logs = list
        notifyDataSetChanged()
    }

}