package com.example.fileexample.view.viewpager

import androidx.fragment.app.Fragment
import com.example.fileexample.R
import com.example.fileexample.view.fragments.FragmentExternal
import com.example.fileexample.view.fragments.FragmentInternal

enum class PagesEnum(val fragment: Fragment, val title : Int) {
    INTERNAL_STORAGE(FragmentInternal(), R.string.fragment_internal_title_text),
    EXTERNAL_STORAGE(FragmentExternal(), R.string.fragment_external_title_text)
}