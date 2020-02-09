package com.example.fileexample.view.viewpager

import android.util.Log
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import java.util.*
import kotlin.collections.ArrayList

class PagerAdapterTabState(val fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private var arrayFragments = ArrayList<Fragment>()
    private var arrayTitles = ArrayList<String>()


    override fun getPageTitle(position: Int): CharSequence? {
        return arrayTitles[position]
    }

    override fun getItem(position: Int): Fragment {
        return arrayFragments[position]
    }

     override fun getItemPosition(`object`: Any): Int {
        val fragment = `object` as Fragment
        val position = arrayFragments.indexOf(fragment)
        if (position >= 0) {
            return position
        } else {
            //если фрагмент был переоздан, то возвращает флаг PagerAdapter.POSITION_NONE, который заставляет обновить View у pager-а
            return PagerAdapter.POSITION_NONE
        }
    }

    override fun getCount(): Int {
        return arrayFragments.size
    }

    fun addFragment(title: String, fragment: Fragment) {
        arrayFragments.add(fragment)
        arrayTitles.add(title)
    }

    fun addFragment(fragment:Fragment,title: String){
        arrayFragments.add(fragment)
        arrayTitles.add(title)

    }
    fun addFragment(fragment: Fragment, title: String, position: Int) {
        if (position > -1 && position < arrayFragments.size) {
            arrayFragments[position] = fragment
            arrayTitles[position] = title
            notifyDataSetChanged()
        }
    }


    fun replaceFragment(position: Int, fragment: Fragment, title: String) {
        if (arrayFragments.size > position && position > -1) {
            arrayFragments[position] = fragment
            arrayTitles[position] = title
            notifyDataSetChanged()
        }
        fun removeFragment(fragment: Fragment, position: Int) {
            if (arrayFragments.size > position && position > 0) {
                arrayFragments.removeAt(position)
                arrayTitles.removeAt(position)
                notifyDataSetChanged()
            }
        }
    }
}