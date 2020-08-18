package com.example.andersen_internship.roomdatabase


import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import java.util.*
import kotlin.collections.ArrayList

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: ArrayList<Movie>): Completable

    @Query("SELECT * FROM movie")
    fun allMovies(): Flowable<List<Movie>>

    @Delete
    fun deleteCollection(collection: Movie)
}