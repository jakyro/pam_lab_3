package com.example.lab2.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lab2.fragments.movieList.AllMoviesViewModel
import com.example.lab2.request.ApiFactory

class AllMoviesViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AllMoviesViewModel(ApiFactory.movieService) as T
    }
}