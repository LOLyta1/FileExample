package com.example.fileexample.view.viewpager

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.example.fileexample.R
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class PagerAdapterFragments(fm: FragmentManager,
                            var arrayFragments: ArrayList<Fragment>,
                            var arrayTitles : ArrayList<String>) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


    override fun getPageTitle(position: Int): CharSequence? {
        return arrayTitles[position]
    }

    override fun getItem(position: Int): Fragment {
        return arrayFragments[position]
    }

    override fun getCount(): Int {
        return arrayFragments.count()
    }

    fun addFragment(fragment:Fragment,title: String){
            arrayFragments.add(fragment)
            arrayTitles.add(title)

    }


    fun addFragment(fragment:Fragment,title: String,position: Int){
        if(arrayFragments.size>position && position>0) {
            arrayFragments.add(position, fragment)
            arrayTitles.add(position, title)
        }
    }

    fun removeFragment(fragment: Fragment, position: Int){
        if(arrayFragments.size>position && position>0){
            arrayFragments.removeAt(position)
            arrayTitles.removeAt(position)
        }

    }
    fun replaceFragment(position: Int,fragment:Fragment, title:String){
        if(arrayFragments.size>position && position>-1){
            arrayFragments[position]=fragment
            arrayTitles[position]=title
        }

    }

}