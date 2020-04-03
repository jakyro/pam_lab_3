package com.example.lab2.views.overview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lab2.model.MovieModel
import com.example.lab2.request.ApiFactory
import com.example.lab2.request.MovieRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class OverviewViewModel : ViewModel() {

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository: MovieRepository = MovieRepository(ApiFactory.movieService)


    val moviesLiveData = MutableLiveData<MutableList<MovieModel>>()

    fun fetchMovies() {
        scope.launch {
            val movies = repository.getMovies()
            moviesLiveData.postValue(movies)
        }
    }


    fun cancelAllRequests() = coroutineContext.cancel()

}