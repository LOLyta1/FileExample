package com.example.fileexample.view.viewpager

import androidx.fragment.app.Fragment
import com.example.fileexample.view.fragments.FragmentInternal

enum class PagesEnum(val fragment: Fragment) {
    INTERNAL_STORAGE(FragmentInternal())
}