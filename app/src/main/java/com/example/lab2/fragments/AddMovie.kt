package com.example.lab2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.lab2.AbstractFragment
import com.example.lab2.R
import com.example.lab2.model.SubmitMovieModel
import com.example.lab2.request.ApiFactory
import com.example.lab2.request.MovieRepository
import kotlinx.coroutines.launch

class AddMovie : AbstractFragment() {
    private val repository: MovieRepository = MovieRepository(ApiFactory.movieService)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view: View = inflater.inflate(R.layout.add_movie, container, false)
        view.findViewById<Button>(R.id.submit_button).setOnClickListener {
            submitMovie(view)
        }
        return view
    }

    private fun submitMovie(view: View) {
        showProgress()
        val data = collectData(view)
        if (data.name.isEmpty() || data.category.isEmpty() || data.thumbnail.isEmpty() || data.year.isEmpty() || data.length.isEmpty()) {
            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_LONG).show()
            dismissProgress()
        } else {
            scope.launch {
                repository.postMovie(data)
                clearForm(view)
                dismissProgress()
                activity?.runOnUiThread {
                    Toast.makeText(context, "Success", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun clearForm(view: View) {
        view.findViewById<TextView>(R.id.edit_movie_name).text = ""
        view.findViewById<TextView>(R.id.edit_movie_category).text = ""
        view.findViewById<TextView>(R.id.edit_movie_thumbnail).text = ""
        view.findViewById<TextView>(R.id.edit_movie_year).text = ""
        view.findViewById<TextView>(R.id.edit_movie_length).text = ""
    }

    private fun collectData(view: View): SubmitMovieModel {
        val name = view.findViewById<TextView>(R.id.edit_movie_name).text
        val category = view.findViewById<TextView>(R.id.edit_movie_category).text
        val thumbnail = view.findViewById<TextView>(R.id.edit_movie_thumbnail).text
        val year = view.findViewById<TextView>(R.id.edit_movie_year).text
        val length = view.findViewById<TextView>(R.id.edit_movie_length).text
        return SubmitMovieModel(
            name = name.toString(),
            category = category.toString(),
            thumbnail = thumbnail.toString(),
            year = year.toString(),
            length = length.toString()
        )
    }
}