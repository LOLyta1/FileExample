package com.example.fileexample.view.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.example.fileexample.R
import com.example.fileexample.presenter.MediaPresenter
import com.example.fileexample.view.interfaces.ITextView
import java.lang.Exception

class FragmentAudio : Fragment(),
    ITextView {
    private lateinit var presenter: MediaPresenter

    companion object {
        val CREATE_FILE = 1
        val OPEN_FILE = 2
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
        return inflater.inflate(R.layout.fragment_audio, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_add_and_save, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_app_specific_save_button -> {
          //      createFile(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, "myfile", "text/plain")
            }
            R.id.menu_app_specific_watch_button -> {
          //      openFile("text/plain", MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
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