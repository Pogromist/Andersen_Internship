package com.example.andersen_internship.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Collection(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String
)
{
    override fun toString(): String {
        return "Collection(id=$id, name='$name')"
    }
}