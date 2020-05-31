package com.example.lab2.request;

import com.example.lab2.model.MovieModel
import com.example.lab2.model.SubmitMovieModel
import com.example.lab2.model.SubmitRatingModel

class MovieRepository(private val api: MovieService) : BaseRepository() {
    suspend fun getMovies(): MutableList<MovieModel>? {
        val movieResponse = safeApiCall(
            call = { api.getMoviesAsync().await() },
            errorMessage = "Error Fetching Movies"
        )
        return movieResponse?.toMutableList()
    }

    suspend fun getTopMovies(): MutableList<MovieModel>? {
        val movieResponse = safeApiCall(
            call = { api.getTopMoviesAsync().await() },
            errorMessage = "Error Fetching Top Movies"
        )
        return movieResponse?.toMutableList()
    }

    suspend fun postMovie(data: SubmitMovieModel): Int? {
        return safeApiCall(
            call = { api.postMovieAsync(data).await() },
            errorMessage = "Error Posting Movie"
        );
    }

    suspend fun getMovie(id: String): MovieModel? {
        return safeApiCall(
            call = { api.getMovieAsync(id).await() },
            errorMessage = "Error Fetching Movie with ID=$id"
        );
    }

    suspend fun postRating(data: SubmitRatingModel): Int? {
        return safeApiCall(
            call = { api.postRatingAsync(data).await() },
            errorMessage = "Error Posting Rating"
        )
    }
}