package com.example.andersen_internship.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = MovieData.TABLE_NAME)
data class MovieData(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    var id: Int? = null,

    @ColumnInfo(name = TITLE)
    var title: String? = null,

    @ColumnInfo(name = DURATION)
    var duration: Int? = null
) {
    companion object {
        const val TABLE_NAME = "movie_details"
        const val ID = "id"
        const val TITLE = "title"
        const val DURATION = "duration"
    }
}