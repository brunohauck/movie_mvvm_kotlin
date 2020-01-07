package com.example.dell.cinesky.service

import com.example.dell.cinesky.model.Movie
import io.reactivex.Observable
import retrofit2.http.GET


interface ApiMovies {

    @GET("/api/movies")
    fun getMovies(): Observable<List<Movie>>

    //Single<List>

}