package com.example.lab2.fragments.movieList;

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.lab2.R
import com.example.lab2.ViewMovie
import com.example.lab2.common.Utils
import com.example.lab2.model.MovieModel
import com.squareup.picasso.Picasso

class MovieListAdapter :
    RecyclerView.Adapter<MovieListAdapter.MyViewHolder>() {

    var data: Array<MovieModel> = emptyArray()

    fun setData(value: Array<MovieModel>, notify: Boolean = true) {
        data = value
        if (notify) {
            notifyDataSetChanged()
        }
    }

    class MyViewHolder(private val context: Context, val view: LinearLayout) :
        RecyclerView.ViewHolder(view), View.OnClickListener {
        lateinit var movieId: String
        override fun onClick(view: View?) {
            val intent = Intent(context, ViewMovie::class.java)
            intent.putExtra("id", movieId)
            startActivity(context, intent, null)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.myitemlayout, parent, false) as LinearLayout
        return MyViewHolder(parent.context, itemLayout)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.view.setOnClickListener(holder)
        val movie = data[position]
        holder.movieId = movie.id

        val movieNameTextViewModel = holder.view.findViewById<TextView>(R.id.movieName)
        movieNameTextViewModel.text = movie.name

        val movieCategoryTextViewModel = holder.view.findViewById<TextView>(R.id.movieCategory)
        movieCategoryTextViewModel.text = movie.category

        val ratingTextViewModel = holder.view.findViewById<TextView>(R.id.rating)
        ratingTextViewModel.text = Utils.renderRating(movie.rating)

        val imageView: ImageView = holder.view.findViewById(R.id.imageView)
        Picasso
            .get()
            .load(movie.thumbnail)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(imageView)
    }

    override fun getItemCount() = data.size
}