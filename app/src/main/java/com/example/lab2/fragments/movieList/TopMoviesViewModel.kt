package com.example.lab2.fragments.movieList

import kotlinx.coroutines.launch

class TopMoviesViewModel: MovieListViewModel() {
    override fun fetchMovies() {
        scope.launch {
            val movies = repository.getTopMovies()
            moviesLiveData.postValue(movies)
        }
    }
}