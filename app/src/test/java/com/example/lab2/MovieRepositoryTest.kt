package com.example.lab2

import com.example.lab2.mock.FakeErrorMovieService
import com.example.lab2.mock.FakeSuccessMovieService
import com.example.lab2.model.MovieModel
import com.example.lab2.model.SubmitMovieModel
import com.example.lab2.model.SubmitRatingModel
import com.example.lab2.request.MovieRepository
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class MovieRepositoryTest {
    private lateinit var repoSuccess: MovieRepository
    private lateinit var repoError: MovieRepository

    @Before
    fun before_test() {
        repoSuccess = MovieRepository(FakeSuccessMovieService())
        repoError = MovieRepository(FakeErrorMovieService())
    }

    @Test(expected = Test.None::class)
    fun testRepositoryGetMovies() {
        var movies: List<MovieModel>? = null
        runBlocking {
            movies = repoSuccess.getMovies()
        }
        assert(movies != null)
        assert(movies?.size != 0)
    }

    @Test(expected = Test.None::class)
    fun testRepositoryGetTopMovies() {
        var movies: List<MovieModel>? = null
        runBlocking {
            movies = repoSuccess.getTopMovies()
        }
        assert(movies != null)
        assert(movies?.size != 0)
    }

    @Test(expected = Test.None::class)
    fun testRepositoryGetMovie() {
        var movies: MovieModel? = null
        runBlocking {
            movies = repoSuccess.getMovie("1")
        }
        assert(movies != null)
    }

    @Test(expected = Test.None::class)
    fun testRepositoryPostRating() {
        var id: Int? = null
        runBlocking {
            id = repoSuccess.postRating(SubmitRatingModel("33", "9"))
        }
        assert(id != null)
    }

    @Test(expected = Test.None::class)
    fun testRepositoryPostMovie() {
        var id: Int? = null
        runBlocking {
            id = repoSuccess.postMovie(SubmitMovieModel("The Movie", "Comedy", "url", "2002", "201"))
        }
        assert(id != null)
    }

    /// ERROR

    @Test(expected = Test.None::class)
    fun testRepositoryGetMoviesError() {
        var movies: List<MovieModel>? = null
        runBlocking {
            movies = repoError.getMovies()
        }
        assert(movies == null)
    }

    @Test(expected = Test.None::class)
    fun testRepositoryGetTopMoviesError() {
        var movies: List<MovieModel>? = null
        runBlocking {
            movies = repoError.getTopMovies()
        }
        assert(movies == null)
    }

    @Test(expected = Test.None::class)
    fun testRepositoryGetMovieError() {
        var movies: MovieModel? = null
        runBlocking {
            movies = repoError.getMovie("1")
        }
        assert(movies == null)
    }

    @Test(expected = Test.None::class)
    fun testRepositoryPostRatingError() {
        var id: Int? = null
        runBlocking {
            id = repoError.postRating(SubmitRatingModel("33", "9"))
        }
        assert(id == null)
    }

    @Test(expected = Test.None::class)
    fun testRepositoryPostMovieError() {
        var id: Int? = null
        runBlocking {
            id = repoError.postMovie(SubmitMovieModel("The Movie", "Comedy", "url", "2002", "201"))
        }
        assert(id == null)
    }

    @After
    fun after_test() {
    }
}