package com.example.dell.cinesky.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface MovieDao {
    @get:Query("SELECT * FROM movie")
    val all: List<Movie>

    @Insert
    fun insertAll(vararg users: Movie)
}