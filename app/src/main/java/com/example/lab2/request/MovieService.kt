package com.example.lab2.request

import com.example.lab2.model.MovieModel
import com.example.lab2.model.SubmitMovieModel
import com.example.lab2.model.SubmitRatingModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MovieService {
    @GET("movies_ratings")
    fun getMoviesAsync(): Deferred<Response<List<MovieModel>>>

    @POST("movies")
    fun postMovieAsync(@Body data: SubmitMovieModel): Deferred<Response<Int>>

    @GET("movies_ratings/{id}")
    fun getMovieAsync(@Path("id") id: String): Deferred<Response<MovieModel>>

    @POST("ratings")
    fun postRatingAsync(@Body data: SubmitRatingModel): Deferred<Response<Int>>
}