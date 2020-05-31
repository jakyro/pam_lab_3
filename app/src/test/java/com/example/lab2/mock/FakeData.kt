package com.example.lab2.mock

import com.example.lab2.model.MovieModel
import okhttp3.ResponseBody
import okhttp3.internal.http.RealResponseBody
import okio.Buffer

class FakeData {
    companion object {
        var MOVIE1 = MovieModel("33", "The Movie", "Comedy", "url", "2002", "178", "8.9")
        var MOVIE2 = MovieModel("34", "Last Movie", "Adventure", "url", "2009", "178", "9.5")
        var MOVIES = listOf(MOVIE1, MOVIE2)

        fun getError(): ResponseBody {
            return RealResponseBody("null", 0, Buffer())
        }
    }
}