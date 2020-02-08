package com.example.fileexample.view.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fileexample.R
import com.example.fileexample.StorageTypes
import com.example.fileexample.presenter.FilePresenter
import com.example.fileexample.view.IView
import com.example.fileexample.view.dialogs.DialogStorage
import com.example.fileexample.view.recyclers.LogAdapter
import kotlinx.android.synthetic.main.fragment_text_file.*
import kotlinx.android.synthetic.main.fragment_text_file.view.*
import java.lang.Exception

class FragmentInternal() :
    Fragment(),
    IView,
    DialogStorage.Listener {

    private var presenter: FilePresenter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = FilePresenter(this)
        return inflater.inflate(R.layout.fragment_text_file, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dialog = DialogStorage().apply {setTargetFragment(this@FragmentInternal, 10)}
        val manager = activity?.supportFragmentManager

        view.save_button.setOnClickListener {
            if (manager != null) {
                dialog.arguments = Bundle().apply { putInt("mode", DialogStorage.SAVE_MODE)}
                dialog.show(manager, "dialog")
            }
        }

        view.load_button.setOnClickListener {
            if (manager != null) {
                dialog.arguments = Bundle().apply { putInt("mode", DialogStorage.LOAD_MODE)}
                dialog.show(manager, "dialog")
            }
        }
        setupRecycler()
    }

    private fun setupRecycler() {
        if (view != null) {
            view!!.logs_recycler_view.apply {
                layoutManager = LinearLayoutManager(mContext)
                adapter = LogAdapter()
            }
            view!!.logs_recycler_view?.addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL).apply {
                    setDrawable(resources.getDrawable(R.drawable.my_devider))
                }
            )
        }
    }

    override val mContext: Context?
        get() = context

    override fun showFileText(fileText: String) {
        logIt(fileText)
        AlertDialog.Builder(mContext)
            .setIcon(android.R.drawable.ic_dialog_info)
            .setTitle("Текст файла:").setMessage(fileText)
            .setPositiveButton("OK") { dialog, which -> dialog.dismiss() }
            .show()

    }

    override fun showError(ex: Exception) {
        val errorText = "Ошибка чтения файла - ${ex}"
        logIt(errorText)
        Toast.makeText(context, errorText, Toast.LENGTH_LONG).show()
    }

    override fun showLog(message: String) {
        logIt(message)
    }

    private fun logIt(text: String) {
        ((view?.logs_recycler_view?.adapter) as LogAdapter).addItem(text)
    }

    override fun dialogOkCLick(storage: StorageTypes, mode: Int?) {
        val fileName = file_name_edit_text.text.toString()
        val fileText = file_text_edit_text.text.toString()
        when(mode){
           DialogStorage.SAVE_MODE->{
               presenter?.createFile(fileName, fileText,storage)
           }
            DialogStorage.LOAD_MODE->{
                presenter?.readFile(fileName,storage)
            }
        }
    }
}