package com.example.andersen_internship.roomdatabase

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MovieDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieData(data: MovieData) : Completable

    @Query("SELECT * FROM ${MovieData.TABLE_NAME}")
    fun getAllRecords(): Single<List<MovieData>>

    @Delete
    fun deleteMovieData(movieData: MovieData) : Completable

    @Update
    fun updateMovieData(movieData: MovieData)
}