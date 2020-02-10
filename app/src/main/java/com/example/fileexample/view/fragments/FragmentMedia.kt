package com.example.fileexample.view.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fileexample.R
import com.example.fileexample.StorageTypes
import com.example.fileexample.presenter.MediaPresenter
import com.example.fileexample.view.IView
import java.lang.Exception

class FragmentMedia : Fragment(),
    IView {
    private lateinit var presenter: MediaPresenter

    companion object {
        val CREATE_FILE = 1
        val OPEN_FILE = 2
    }

    fun createFile(uri: Uri, name: String, fileType: String) {
        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            putExtra(Intent.EXTRA_TITLE, name)
            putExtra(Intent.EXTRA_TEXT,"test file")
            type = fileType
            putExtra(DocumentsContract.EXTRA_INITIAL_URI, uri)
        }
        try {
            startActivityForResult(intent, CREATE_FILE)
        } catch (ex: Exception) {
        }
    }


    fun openFile(fileType: String?, uri: Uri) {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = fileType
            putExtra(DocumentsContract.EXTRA_INITIAL_URI, uri)
        }
        try {
            startActivityForResult(intent, OPEN_FILE)
        } catch (ex: Exception) {
            Toast.makeText(context, "Ошибка открытия файла : ${ex.message}", Toast.LENGTH_LONG)
                .show()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        presenter = MediaPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_external, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_add_and_save, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_app_specific_save_button -> {
                createFile(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, "myfile", "text/plain")
            }
            R.id.menu_app_specific_watch_button -> {
                openFile("text/plain", MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override val mContext: Context?
        get() = context

    override fun showFileText(fileText: String) {
        Log.d("mylog", fileText)
    }

    override fun showError(ex: Exception) {
        Log.d("mylog", ex.message)
    }

    override fun showLog(message: String) {
        Log.d("mylog", message)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            OPEN_FILE ->{
                Log.d("mylog", "onActivityResult - результат открытия ")
            }

            CREATE_FILE ->{
                Log.d("mylog", "onActivityResult - результат сохранения ")
            }
        }
    }
}