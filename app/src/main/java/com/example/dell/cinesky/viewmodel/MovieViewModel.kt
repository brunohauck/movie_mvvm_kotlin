package com.example.dell.cinesky.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.example.dell.cinesky.base.BaseViewModel
import com.example.dell.cinesky.model.Movie

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.example.dell.cinesky.R
import com.squareup.picasso.Picasso


class MovieViewModel: BaseViewModel() {
    private val movieTitle = MutableLiveData<String>()
    //private val movieOverview = MutableLiveData<String>()
    private val movieCover = MutableLiveData<String>()

    fun bind(movie: Movie){
        movieTitle.value = movie.title
        movieCover.value = movie.cover_url
       // movieOverview.value = movie.overview
    }

    fun getMovieTitle():MutableLiveData<String>{
        return movieTitle
    }

    fun getMovieCover():MutableLiveData<String>{
        return movieCover
    }

    /*
    fun getImageUrl(): String {
        // The URL will usually come from a model (i.e Profile)
        return "http://cdn.meme.am/instances/60677654.jpg"
    }


    @BindingAdapter("bind:imageUrl")
    fun loadImage(view: ImageView,  imageUrl: String) {

        Picasso.get()
                .load(imageUrl)
                .resize(50, 50)
                .centerCrop()
                .into(view)
    }



    fun getMovieOverview():MutableLiveData<String>{
        return movieOverview
    }*/
}