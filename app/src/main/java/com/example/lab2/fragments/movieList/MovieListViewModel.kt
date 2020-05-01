package com.example.lab2.fragments.movieList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lab2.model.MovieModel
import com.example.lab2.request.ApiFactory
import com.example.lab2.request.MovieRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class MovieListViewModel : ViewModel() {

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    protected val scope = CoroutineScope(coroutineContext)

    protected val repository: MovieRepository = MovieRepository(ApiFactory.movieService)


    val moviesLiveData = MutableLiveData<MutableList<MovieModel>>()

    abstract fun fetchMovies()

    fun cancelAllRequests() = coroutineContext.cancel()
}