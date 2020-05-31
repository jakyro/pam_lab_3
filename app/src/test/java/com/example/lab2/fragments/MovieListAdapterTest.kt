package com.example.lab2.fragments

import com.example.lab2.fragments.movieList.MovieListAdapter
import com.example.lab2.mock.FakeData
import org.junit.Before
import org.junit.Test

class MovieListAdapterTest {

    private lateinit var movieListAdapter: MovieListAdapter

    @Before
    fun before_test() {
        movieListAdapter = MovieListAdapter()
    }

    @Test
    fun testSetData() {
        movieListAdapter.setData(FakeData.MOVIES.toTypedArray(), false)
        assert(movieListAdapter.itemCount == FakeData.MOVIES.size)
    }

    @Test(expected = NullPointerException::class)
    fun testSetDataNotify() {
        movieListAdapter.setData(FakeData.MOVIES.toTypedArray())
        assert(movieListAdapter.itemCount == FakeData.MOVIES.size)
    }
}