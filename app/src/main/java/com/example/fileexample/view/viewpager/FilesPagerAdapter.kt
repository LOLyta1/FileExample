package com.example.fileexample.view.viewpager

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter

class FilesPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
     override fun getItem(position: Int): Fragment {
         return PagesEnum.values()[position].fragment
    }

    override fun getCount(): Int {
        return PagesEnum.values().count()
    }
}