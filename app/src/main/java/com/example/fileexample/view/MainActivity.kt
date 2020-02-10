package com.example.fileexample.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import com.example.fileexample.R
import com.example.fileexample.presenter.MediaPresenter
import com.example.fileexample.view.fragments.*
import com.example.fileexample.view.viewpager.PagerAdapterTabState
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        view_pager.adapter = PagerAdapterTabState(supportFragmentManager).apply {
            addFragment("App Specific", FragmentAppSpecific())
            addFragment("Media", FragmentMedia())
            addFragment("Shared pref", FragmentSharedPref())
            addFragment("Database",FragmentDatabase())
        }
        tab_layout.setupWithViewPager(view_pager, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_activity_show_logs_item -> {
                if(supportFragmentManager.findFragmentByTag("log_fragment_transaction")==null) {
                    supportFragmentManager.beginTransaction()
                        .addToBackStack("log_fragment_transaction")
                        .replace(R.id.container_logs_app, FragmentLogs())
                        .commit()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
