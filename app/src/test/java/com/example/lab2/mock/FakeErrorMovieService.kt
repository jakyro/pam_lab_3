package com.example.lab2.mock

import com.example.lab2.mock.FakeData.Companion.getError
import com.example.lab2.model.MovieModel
import com.example.lab2.model.SubmitMovieModel
import com.example.lab2.model.SubmitRatingModel
import com.example.lab2.request.MovieService
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import retrofit2.Response

class FakeErrorMovieService : MovieService {
    override fun getMoviesAsync(): Deferred<Response<List<MovieModel>>> {
        val deferred: CompletableDeferred<Response<List<MovieModel>>> = CompletableDeferred()
        deferred.complete(Response.error(500, getError()))
        return deferred
    }

    override fun getTopMoviesAsync(): Deferred<Response<List<MovieModel>>> {
        val deferred: CompletableDeferred<Response<List<MovieModel>>> = CompletableDeferred()
        deferred.complete(Response.error(500, getError()))
        return deferred
    }

    override fun postMovieAsync(data: SubmitMovieModel): Deferred<Response<Int>> {
        val deferred: CompletableDeferred<Response<Int>> = CompletableDeferred()
        deferred.complete(Response.error(500, getError()))
        return deferred
    }

    override fun getMovieAsync(id: String): Deferred<Response<MovieModel>> {
        val deferred: CompletableDeferred<Response<MovieModel>> = CompletableDeferred()
        deferred.complete(Response.error(500, getError()))
        return deferred
    }

    override fun postRatingAsync(data: SubmitRatingModel): Deferred<Response<Int>> {
        val deferred: CompletableDeferred<Response<Int>> = CompletableDeferred()
        deferred.complete(Response.error(500, getError()))
        return deferred
    }

}
