package com.example.lab2

import com.example.lab2.fragments.movieList.TopMoviesViewModel
import com.example.lab2.mock.FakeSuccessMovieService
import org.junit.After
import org.junit.Before
import org.junit.Test

class TopMoviesViewModelTest {
    private lateinit var viewModel: TopMoviesViewModel

    @Before
    fun before_test() {
        viewModel = TopMoviesViewModel(FakeSuccessMovieService())
    }

    @Test(expected = Test.None::class)
    fun testFetchMoviesWithoutException() {
        viewModel.fetchMovies()
    }

    @After
    fun after_test() {
    }
}