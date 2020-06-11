package com.example.lab2.fragments.movieList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab2.AbstractFragment
import com.example.lab2.R

class MovieList(private val titleRes: Int, private val viewModel: MovieListViewModel) :
    AbstractFragment() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view: View = inflater.inflate(R.layout.overview, container, false)
        val viewAdapter = MovieListAdapter()
        view.findViewById<TextView>(R.id.overview_title).setText(titleRes)
        recyclerView = view.findViewById<RecyclerView>(R.id.my_recycler_view).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewAdapter
        }
        viewModel.moviesLiveData.observe(viewLifecycleOwner, Observer {
            viewAdapter.setData(it.toTypedArray())
            dismissProgress()
        })
        return view
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchMovies()
        showProgress()
    }
}