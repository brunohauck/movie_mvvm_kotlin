package com.example.dell.cinesky.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.dell.cinesky.model.Movie
import com.example.dell.cinesky.model.MovieDao


@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): MovieDao
}