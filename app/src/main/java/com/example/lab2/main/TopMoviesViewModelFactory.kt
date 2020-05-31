package com.example.lab2.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lab2.fragments.movieList.TopMoviesViewModel
import com.example.lab2.request.ApiFactory

class TopMoviesViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TopMoviesViewModel(ApiFactory.movieService) as T
    }
}