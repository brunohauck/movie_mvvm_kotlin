package com.example.dell.cinesky.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Movie(

        @field:PrimaryKey
        var id: String,
        var title: String,
        var overview: String,
        var duration: String,
        var release_year: String,
        var cover_url: String
        //var backdrops_url: String? = null
)




