package com.example.lab2.fragments

import com.example.lab2.MainActivity
import com.example.lab2.fragments.movieList.AllMoviesViewModel
import com.example.lab2.fragments.movieList.MovieList
import com.example.lab2.mock.FakeSuccessMovieService
import org.junit.After
import org.junit.Before
import org.junit.Test

class MovieListTest {

    private lateinit var movieList: MovieList

    @Before
    fun before_test() {
        movieList = MovieList(AllMoviesViewModel(FakeSuccessMovieService()))
    }

    @Test(expected = IllegalStateException::class)
    fun testOnCreateView() {
        movieList.onCreateView(MainActivity().layoutInflater, null, null)
    }

    @After
    fun after_test() {
    }
}