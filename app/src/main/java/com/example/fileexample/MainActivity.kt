package com.example.fileexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.fileexample.view.fragments.FragmentExternal
import com.example.fileexample.view.fragments.FragmentInternal
import com.example.fileexample.view.viewpager.PagerAdapterFragments
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        view_pager.adapter= PagerAdapterFragments(supportFragmentManager, arrayListOf<Fragment>(FragmentInternal(),FragmentExternal()),
            arrayListOf("Internal","External","") )
        tab_layout.setupWithViewPager(view_pager,false)
    }
}
