package com.example.lab2

import com.example.lab2.model.MovieModel
import com.example.lab2.model.SubmitMovieModel
import com.example.lab2.model.SubmitRatingModel
import org.junit.Test
import com.example.lab2.request.Result

class ModelTest {
    @Test
    fun testMovieModel() {
        val data = MovieModel(
            "1",
            "name",
            "comedy",
            "url",
            "2006",
            "201",
            "9.2"
        )
        assert(data.id == "1")
        assert(data.name == "name")
        assert(data.category == "comedy")
        assert(data.thumbnail == "url")
        assert(data.year == "2006")
        assert(data.length == "201")
        assert(data.rating == "9.2")
    }

    @Test
    fun testSubmitMovieModel() {
        val data = SubmitMovieModel("name", "comedy", "url", "2006", "201")
        assert(data.name == "name")
        assert(data.category == "comedy")
        assert(data.thumbnail == "url")
        assert(data.year == "2006")
        assert(data.length == "201")
        assert(data.valid())
    }

    @Test
    fun testSubmitRatingModel() {
        val data = SubmitRatingModel("10", "9")
        assert(data.movieId == "10")
        assert(data.rating == "9")
    }

    @Test
    fun testResultSuccess() {
        val result = Result.Success("Hello")
        assert(result.data == "Hello")
    }

    @Test
    fun testResultError() {
        val result = Result.Error(Exception("Error Happened"))
        assert(result.exception.message == "Error Happened")
    }
}