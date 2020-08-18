package com.example.andersen_internship.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey
    @ColumnInfo(name = ID)
    val id: Int,

    @ColumnInfo(name = TITLE)
    val title: String
) {
    companion object {
        const val TITLE = "title"
        const val ID = "id"
    }
}