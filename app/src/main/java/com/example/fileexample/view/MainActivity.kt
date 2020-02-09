package com.example.fileexample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.fileexample.R
import com.example.fileexample.view.fragments.FragmentMedia
import com.example.fileexample.view.fragments.FragmentAppSpecific
import com.example.fileexample.view.viewpager.PagerAdapterTabState
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         view_pager.adapter = PagerAdapterTabState(supportFragmentManager).apply {
            addFragment("AppSpecific",FragmentAppSpecific())
            addFragment("Media",FragmentMedia())
        }

        tab_layout.setupWithViewPager(view_pager, false)
    }

}
