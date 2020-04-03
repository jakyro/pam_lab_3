package com.example.lab2.views

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.lab2.R
import com.example.lab2.model.SubmitMovieModel
import com.example.lab2.request.ApiFactory
import com.example.lab2.request.MovieRepository
import com.example.lab2.views.overview.OverviewViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class AddMovie : Fragment() {
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default
    private lateinit var progress: ProgressDialog
    private val scope = CoroutineScope(coroutineContext)
    private val repository: MovieRepository = MovieRepository(ApiFactory.movieService)
    private val viewModel: OverviewViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        progress = ProgressDialog(this.context)
        progress.setTitle("Loading")
        progress.setMessage("Wait while loading...")
        progress.setCancelable(false)

        val view: View = inflater.inflate(R.layout.add_movie, container, false)
        view.findViewById<Button>(R.id.submit_button).setOnClickListener {
            progress.show();
            val data = collectData(view)
            if (data.name.isEmpty() || data.category.isEmpty() || data.thumbnail.isEmpty() || data.year.isEmpty() || data.length.isEmpty()) {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_LONG).show()
            } else {
                scope.launch {
                    repository.postMovie(data)
                    clearForm(view)
                    viewModel.fetchMovies()
                    progress.dismiss()
                    activity?.runOnUiThread {
                        Toast.makeText(context, "Success", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        return view
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