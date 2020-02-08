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
import androidx.recyclerview.widget.RecyclerView
import com.example.fileexample.R
import com.example.fileexample.StorageTypes
import com.example.fileexample.presenter.FilePresenter
import com.example.fileexample.view.IView
import com.example.fileexample.view.recycler.LogAdapter
import kotlinx.android.synthetic.main.fragment_text_file.*
import kotlinx.android.synthetic.main.fragment_text_file.view.*
import java.lang.Exception

class FragmentInternal() :
    Fragment(),
    IView {

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

        setupRecycler()

        view.save_button.setOnClickListener {
            val fileName = file_name_edit_text.text.toString()
            val fileText = file_text_edit_text.text.toString()
            lateinit var storageType:StorageTypes

            when(radio_group.checkedRadioButtonId){
                R.id.internal_text_file_radio_button -> storageType=StorageTypes.INTERNAL
                R.id.external_text_file_radio_button -> storageType=StorageTypes.EXTERNAL_MEDIA
                R.id.internal_cahe_text_file_radio_button ->storageType=StorageTypes.INTERNAL_CACHE
                R.id.external_cache_text_file_radio_button ->storageType=StorageTypes.EXTERNAL_CACHE
            }
                presenter?.createFile(fileName,storageType)
                presenter?.saveToFile(fileName, fileText)
        }


        view.load_button.setOnClickListener {
            presenter?.readFile(file_name_edit_text.text.toString())
        }

        logs_recycler_view.apply {
            layoutManager=LinearLayoutManager(mContext)
            adapter=LogAdapter()
        }

    }

    private fun setupRecycler() {
        val decorator=DividerItemDecoration(context,DividerItemDecoration.HORIZONTAL)
        decorator.setDrawable(resources.getDrawable(R.drawable.my_devider))
        view?.logs_recycler_view?.addItemDecoration(decorator)
    }

    override val mContext: Context?
        get() = context

    override fun showFileText(fileText: String) {
      AlertDialog.Builder(mContext).
          setIcon(android.R.drawable.ic_dialog_info).
          setTitle("Текст файла:").
          setMessage(fileText).
          setPositiveButton("OK") { dialog, which ->dialog.dismiss()}.
          show()
        ((logs_recycler_view.adapter) as LogAdapter).addItem("Текст файла: $fileText")
    }

    override fun showError(ex: Exception) {
        val errorText="Ошибка чтения файла - ${ex}"
        Toast.makeText(context, errorText, Toast.LENGTH_LONG).show()
        ((logs_recycler_view.adapter) as LogAdapter).addItem(errorText)
    }

    override fun showLog(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        ((logs_recycler_view.adapter) as LogAdapter).addItem(message)
    }


}