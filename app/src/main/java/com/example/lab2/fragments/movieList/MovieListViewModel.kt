package com.example.lab2.fragments.movieList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lab2.model.MovieModel
import com.example.lab2.request.MovieRepository
import com.example.lab2.request.MovieService
import kotlinx.coroutines.*

abstract class MovieListViewModel(service: MovieService) : ViewModel() {

    private val parentJob = Job()

    private val coroutineContext
        get() = parentJob + Dispatchers.Default

    protected val scope = CoroutineScope(coroutineContext)

    protected val repository: MovieRepository = MovieRepository(service)


    val moviesLiveData = MutableLiveData<MutableList<MovieModel>>()

    abstract fun fetchMovies()
}