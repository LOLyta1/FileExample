package com.example.fileexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fileexample.view.viewpager.FilesPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        view_pager.adapter= FilesPagerAdapter(supportFragmentManager)
        tab_layout.setupWithViewPager(view_pager)
    }


}
