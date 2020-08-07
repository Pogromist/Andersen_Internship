package com.example.andersen_internship.roomdatabase

import androidx.room.*

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCollection(collection: List<Collection>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setLinksCollectionMovies(collectionMovies: List<CollectionMovies>)

    @Query("SELECT * FROM collection")
    fun allCollections(): List<Collection>

    @Query("SELECT * FROM movie")
    fun allMovies(): List<Movie>

    @Delete
    fun deleteCollection(collection: Collection)
}