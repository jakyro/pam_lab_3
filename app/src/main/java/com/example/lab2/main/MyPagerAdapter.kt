package com.example.lab2.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.lab2.views.overview.Overview
import com.example.lab2.views.AddMovie

class MyPagerAdapter(fragmentManager: FragmentManager?) :
    FragmentPagerAdapter(fragmentManager!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    // Returns total number of pages
    override fun getCount(): Int {
        return NUM_ITEMS
    }

    // Returns the fragment to display for that page
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> Overview()
            1 -> AddMovie()
            else -> Overview()
        }
    }

    companion object {
        private const val NUM_ITEMS = 2
    }
}