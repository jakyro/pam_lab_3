package com.example.lab2.fragments.movieList

import com.example.lab2.request.MovieService
import kotlinx.coroutines.launch

class TopMoviesViewModel(service: MovieService) : MovieListViewModel(service) {
    override fun fetchMovies() {
        scope.launch {
            val movies = repository.getTopMovies()
            moviesLiveData.postValue(movies)
        }
    }
}