package com.example.lab2

import com.example.lab2.fragments.movieList.AllMoviesViewModel
import com.example.lab2.mock.FakeSuccessMovieService
import org.junit.After
import org.junit.Before
import org.junit.Test

class AllMoviesViewModelTest {
    private lateinit var viewModel: AllMoviesViewModel

    @Before
    fun before_test() {
        viewModel = AllMoviesViewModel(FakeSuccessMovieService())
    }

    @Test(expected = Test.None::class)
    fun testFetchMoviesWithoutException() {
        viewModel.fetchMovies()
    }

    @After
    fun after_test() {
    }
}