package com.example.fileexample.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fileexample.R
import com.example.fileexample.presenter.FileLib
import kotlinx.android.synthetic.main.fragment_internal.*
import kotlinx.android.synthetic.main.fragment_internal.view.*

class FragmentInternal: Fragment() {

    private var assistent: FileLib?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        assistent= FileLib(context)
        return inflater.inflate(R.layout.fragment_internal,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.save_button.setOnClickListener {
            val fileParams=getFileNameContent()
            if(input_edit_text.text!=null && file_content_edit_text.text!=null){
                assistent?.createFile(FileLib.INTERNAL, fileParams.first, null)
                assistent?.saveToFile(fileParams.first, fileParams.second)
            }else showErrorToast()
        }

        view.load_button.setOnClickListener {
            if (input_edit_text.text!=null){
                file_text_text_view.text=assistent?.readFile(getFileNameContent().first)
            }else showErrorToast()
        }
    }

    private fun getFileNameContent() : Pair<String, String>{
        val fileName=input_edit_text.text.toString()
        val fileContent=file_content_edit_text.text.toString()
        return Pair(fileName,fileContent)
    }

    private fun showErrorToast(){
        Toast.makeText(context,"Заполните все поля", Toast.LENGTH_LONG).show()
    }
}