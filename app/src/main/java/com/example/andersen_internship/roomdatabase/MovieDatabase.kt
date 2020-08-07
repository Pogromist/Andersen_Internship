package com.example.andersen_internship.roomdatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Collection::class, Movie::class, CollectionMovies::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao() : MovieDao

}