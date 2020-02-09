package com.example.fileexample.view.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.fileexample.R
import com.example.fileexample.StorageTypes
import com.example.fileexample.presenter.FilePresenter
import com.example.fileexample.view.IView
import com.example.fileexample.view.dialogs.DialogStorage
import com.example.fileexample.view.viewpager.PagerAdapterTabState
import com.example.fileexample.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_app_specific.*
import kotlinx.android.synthetic.main.fragment_app_specific.view.*
import java.lang.Exception

class FragmentAppSpecific() :
    Fragment(),
    IView,
    DialogStorage.Listener {

    private var presenter: FilePresenter? = null
    private  var  viewModel: SharedViewModel?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = FilePresenter(this)
        return inflater.inflate(R.layout.fragment_app_specific, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dialog = DialogStorage().apply {setTargetFragment(this@FragmentAppSpecific, 10)}
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

        view.show_logs_button.setOnClickListener{
            val fragment=FragmentLogs()
            activity?.supportFragmentManager?.beginTransaction()?.addToBackStack("log_fragment_transaction")?.replace(R.id.container_logs_app,fragment)?.commit()
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
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    private fun logIt(text: String) {
        if(viewModel==null){
            viewModel=activity?.let{ViewModelProviders.of(it).get(SharedViewModel::class.java)}
        }
        viewModel?.logsList?.value?.add(text)
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