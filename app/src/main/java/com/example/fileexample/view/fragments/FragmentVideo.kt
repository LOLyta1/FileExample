package com.example.fileexample.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.fileexample.R
import com.example.fileexample.model.VideoFile
import com.example.fileexample.presenter.VideoFilePresenter
import com.example.fileexample.view.interfaces.IVideoView
import com.example.fileexample.viewmodel.SharedViewModel

class FragmentVideo: Fragment(), IVideoView {

    private var presenter=VideoFilePresenter(this)
    private lateinit var viewModel:SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        viewModel=ViewModelProviders.of(this).get(SharedViewModel::class.java)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_video,container,false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_video_file,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.menu_video_save_button->{}
            R.id.menu_video_watch_button->{}
            R.id.menu_video_show_list_button->{}
        }
        return super.onOptionsItemSelected(item)
    }

    override val mContext: Context?
        get() = context


    override fun showVideoList() {

     }

    override fun showVideoFile(video: VideoFile) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLog(log: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}