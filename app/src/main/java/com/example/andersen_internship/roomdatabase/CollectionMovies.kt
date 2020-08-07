package com.example.andersen_internship.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Collection::class,
        parentColumns = ["id"],
        childColumns = ["collection_id"]
    ),
        ForeignKey(
            entity = Movie::class,
            parentColumns = ["id"],
            childColumns = ["movie_id"]
        )]
)
data class CollectionMovies(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "collection_id")
    val collectionId: Int,

    @ColumnInfo(name = "movie_id")
    val movieId: Int
)