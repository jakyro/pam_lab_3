package com.example.lab2.main

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.lab2.R
import com.example.lab2.fragments.AddMovie
import com.example.lab2.fragments.movieList.*

class MyPagerAdapter(fragmentManager: FragmentManager?, activity: AppCompatActivity) :
    FragmentPagerAdapter(fragmentManager!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val allMovies: AllMoviesViewModel by activity.viewModels { AllMoviesViewModelFactory() }
    private val topMovies: TopMoviesViewModel by activity.viewModels { TopMoviesViewModelFactory() }

    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MovieList(R.string.top_week, topMovies)
            1 -> MovieList(R.string.all_movies, allMovies)
            2 -> AddMovie()
            else -> MovieList(R.string.movies, allMovies)
        }
    }
}