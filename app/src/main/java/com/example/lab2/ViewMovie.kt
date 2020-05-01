package com.example.lab2

import android.os.Bundle
import android.widget.*
import com.example.lab2.model.MovieModel
import com.example.lab2.model.SubmitRatingModel
import com.example.lab2.request.ApiFactory
import com.example.lab2.request.MovieRepository
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import java.math.RoundingMode

class ViewMovie : AbstractActivity() {

    lateinit var movie: MovieModel
    private val repository: MovieRepository = MovieRepository(ApiFactory.movieService)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_movie)
        showProgress()
        val spinner: Spinner = findViewById(R.id.spinner)
        ArrayAdapter
            .createFromResource(this, R.array.ratings, android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }
        findViewById<Button>(R.id.set_rating).setOnClickListener {
            showProgress()
            saveRating(spinner.selectedItem.toString())
        }
        loadMovie()
    }

    private fun saveRating(rating: String) {
        scope.launch {
            val resp = repository.postRating(SubmitRatingModel(movie.id, rating))
            loadMovie()
        }
    }

    private fun loadMovie() {
        scope.launch {
            val id = intent.getStringExtra("id")!!
            val resp = repository.getMovie(id)
            movie = resp!!
            runOnUiThread { setMovieData() }
        }
    }

    private fun setMovieData() {
        val movieNameTextViewModel = findViewById<TextView>(R.id.movieName)
        movieNameTextViewModel.text = movie.name

        val movieCategoryTextViewModel = findViewById<TextView>(R.id.movieCategory)
        movieCategoryTextViewModel.text = movie.category

        val ratingTextViewModel = findViewById<TextView>(R.id.rating)
        ratingTextViewModel.text =
            movie.rating?.toBigDecimal()?.setScale(1, RoundingMode.HALF_EVEN)?.toPlainString()
                ?: "N / A"

        val imageView: ImageView = findViewById(R.id.imageView)
        Picasso
            .get()
            .load(movie.thumbnail)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(imageView)
        dismissProgress()
    }
}
