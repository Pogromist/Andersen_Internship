package com.example.andersen_internship.roomdatabase

import android.content.Context
import androidx.room.Room
import io.reactivex.Completable
import io.reactivex.Flowable

object DatabaseRepository {

    private lateinit var movieDatabase: MovieDatabase

    fun buildDatabase(context: Context) {
        movieDatabase =
            Room.databaseBuilder(context, MovieDatabase::class.java, "movie_database")
                .allowMainThreadQueries()
                .build()
    }

    fun getAllMovies(): Flowable<List<Movie>> {
        return movieDatabase.movieDao().allMovies()
    }

    fun addMovies(movie: ArrayList<Movie>): Completable {
        return movieDatabase.movieDao().insertMovie(movie)
    }
}