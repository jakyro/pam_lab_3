package com.example.lab2.views.overview

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab2.R


class Overview : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private val viewModel: OverviewViewModel by activityViewModels()
    private lateinit var progress: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        progress = ProgressDialog(this.context)
        progress.setTitle("Loading")
        progress.setMessage("Wait while loading...")
        progress.setCancelable(false)

        val view: View = inflater.inflate(R.layout.overview, container, false)
        val viewAdapter = MovieListAdapter()

        recyclerView = view.findViewById<RecyclerView>(R.id.my_recycler_view).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewAdapter
        }

        viewModel.fetchMovies()
        progress.show()
        viewModel.moviesLiveData.observe(viewLifecycleOwner, Observer {
            viewAdapter.data = it.toTypedArray()
            progress.dismiss()
        })
        return view
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchMovies()
    }
}