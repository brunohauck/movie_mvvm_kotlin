package com.example.dell.cinesky.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.dell.cinesky.R
import com.example.dell.cinesky.databinding.ItemMovieBinding
import com.example.dell.cinesky.model.Movie
import com.example.dell.cinesky.viewmodel.MovieViewModel

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private lateinit var movieList:List<Movie>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        val binding: ItemMovieBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_movie, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int {
        return if(::movieList.isInitialized) movieList.size else 0
    }

    fun updateMovieList(movieList:List<Movie>){
        this.movieList = movieList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root){
        private val viewModel = MovieViewModel()

        fun bind(movie:Movie){
            viewModel.bind(movie)

            binding.viewModel = viewModel

        }
    }
}