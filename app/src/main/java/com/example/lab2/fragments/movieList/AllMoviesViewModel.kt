package com.example.lab2.fragments.movieList

import kotlinx.coroutines.launch

class AllMoviesViewModel: MovieListViewModel() {
    override fun fetchMovies() {
        scope.launch {
            val movies = repository.getMovies()
            moviesLiveData.postValue(movies)
        }
    }
}