package com.example.lab2.mock

import com.example.lab2.mock.FakeData.Companion.MOVIE1
import com.example.lab2.mock.FakeData.Companion.MOVIES
import com.example.lab2.model.MovieModel
import com.example.lab2.model.SubmitMovieModel
import com.example.lab2.model.SubmitRatingModel
import com.example.lab2.request.MovieService
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import retrofit2.Response

class FakeSuccessMovieService : MovieService {
    override fun getMoviesAsync(): Deferred<Response<List<MovieModel>>> {
        val deferred: CompletableDeferred<Response<List<MovieModel>>> = CompletableDeferred()
        deferred.complete(Response.success(200, MOVIES))
        return deferred
    }

    override fun getTopMoviesAsync(): Deferred<Response<List<MovieModel>>> {
        val deferred: CompletableDeferred<Response<List<MovieModel>>> = CompletableDeferred()
        deferred.complete(Response.success(200, MOVIES))
        return deferred
    }

    override fun postMovieAsync(data: SubmitMovieModel): Deferred<Response<Int>> {
        val deferred: CompletableDeferred<Response<Int>> = CompletableDeferred()
        deferred.complete(Response.success(33))
        return deferred
    }

    override fun getMovieAsync(id: String): Deferred<Response<MovieModel>> {
        val deferred: CompletableDeferred<Response<MovieModel>> = CompletableDeferred()
        deferred.complete(Response.success(MOVIE1))
        return deferred
    }

    override fun postRatingAsync(data: SubmitRatingModel): Deferred<Response<Int>> {
        val deferred: CompletableDeferred<Response<Int>> = CompletableDeferred()
        deferred.complete(Response.success(33))
        return deferred
    }

}
