package com.example.lab2

import com.example.lab2.fragments.AddMovie
import com.example.lab2.fragments.movieList.MovieList
import com.example.lab2.main.MyPagerAdapter
import org.junit.Before
import org.junit.Test
import java.lang.IllegalStateException

class MainActivityTest {

    private lateinit var activity: MainActivity
    private lateinit var pagerAdapter: MyPagerAdapter

    @Before
    fun init() {
        activity = MainActivity()
        pagerAdapter = MyPagerAdapter(activity.supportFragmentManager, activity)
    }

    @Test(expected = IllegalStateException::class)
    fun testPageAdapterItems0() {
        assert(pagerAdapter.getItem(0) is MovieList)
    }

    @Test(expected = IllegalStateException::class)
    fun testPageAdapterItems1() {
        assert(pagerAdapter.getItem(1) is MovieList)
    }

    @Test
    fun testPageAdapterItems2() {
        assert(pagerAdapter.getItem(2) is AddMovie)
    }

    @Test(expected = IllegalStateException::class)
    fun testPageAdapterItems3() {
        assert(pagerAdapter.getItem(3) is MovieList)
    }

    @Test
    fun testPageAdapterCount() {
        assert(pagerAdapter.count == 3)
    }
}