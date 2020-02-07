package com.example.fileexample.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fileexample.R
import com.example.fileexample.presenter.FilePresenter
import com.example.fileexample.view.IView
import com.example.fileexample.view.viewpager.PagerAdapterFragments
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_internal.*
import kotlinx.android.synthetic.main.fragment_internal.view.*
import okhttp3.internal.notify
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
        return inflater.inflate(R.layout.fragment_internal, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.save_button.setOnClickListener {
            val fileName = file_name_edit_text.text.toString()
            val fileText = file_text_edit_text.text.toString()
            presenter?.createFileInternal(fileName)
            presenter?.saveToFile(fileName, fileText)
        }

        view.load_button.setOnClickListener {
            presenter?.readFile(file_name_edit_text.text.toString())
        }

    }

    override val mContext: Context?
        get() = context

    override fun showFileText(fileText: String) {
       val adapter = activity?.view_pager?.adapter as PagerAdapterFragments

        val fragment = FragmentSavedText()
        fragment.arguments=Bundle().apply { putString("text", fileText)}
      adapter.addFragment(fragment,"Текст из файла")
        adapter.replaceFragment(0,FragmentSavedText(),"Сохранка")
        adapter.notifyDataSetChanged()
    }

    override fun showError(ex: Exception) {
        Toast.makeText(context, "Ошибка чтения файла - ${ex}", Toast.LENGTH_LONG).show()
    }

    override fun showInfoMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }


}