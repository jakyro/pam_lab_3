package com.example.lab2

import com.example.lab2.mock.FakeErrorMovieService
import com.example.lab2.mock.FakeSuccessMovieService
import com.example.lab2.model.MovieModel
import com.example.lab2.request.MovieService
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class MovieServiceTest {
    private lateinit var success: MovieService
    private lateinit var error: MovieService

    @Before
    fun before_test() {
        success = FakeSuccessMovieService()
        error = FakeErrorMovieService()
    }

    @Test(expected = Test.None::class)
    fun testApiGetMoviesAsyncSuccess() {
        var movies: List<MovieModel>? = null
        runBlocking {
            movies = success.getMoviesAsync().await().body()
        }
        assert(movies != null)
        assert(movies?.size == 2)
    }

    @Test(expected = Test.None::class)
    fun testApiGetMoviesAsyncError() {
        var movies: List<MovieModel>? = null
        runBlocking {
            movies = error.getMoviesAsync().await().body()
        }
        assert(movies == null)
    }

    @Test(expected = Test.None::class)
    fun testApiGetTopMoviesAsyncSuccess() {
        var movies: List<MovieModel>? = null
        runBlocking {
            movies = success.getTopMoviesAsync().await().body()
        }
        assert(movies != null)
        assert(movies?.size == 2)
    }

    @Test(expected = Test.None::class)
    fun testApiGetTopMoviesAsyncError() {
        var movies: List<MovieModel>? = null
        runBlocking {
            movies = error.getTopMoviesAsync().await().body()
        }
        assert(movies == null)
    }

    @After
    fun after_test() {
    }
}