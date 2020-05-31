package com.example.lab2.main

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.lab2.fragments.AddMovie
import com.example.lab2.fragments.movieList.AllMoviesViewModel
import com.example.lab2.fragments.movieList.MovieList
import com.example.lab2.fragments.movieList.TopMoviesViewModel

class MyPagerAdapter(fragmentManager: FragmentManager?, activity: AppCompatActivity) :
    FragmentPagerAdapter(fragmentManager!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val allMovies: AllMoviesViewModel by activity.viewModels()
    private val topMovies: TopMoviesViewModel by activity.viewModels()

    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MovieList(topMovies)
            1 -> MovieList(allMovies)
            2 -> AddMovie()
            else -> MovieList(allMovies)
        }
    }
}