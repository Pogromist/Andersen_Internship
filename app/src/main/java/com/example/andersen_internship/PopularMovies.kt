package com.example.andersen_internship

import androidx.room.Entity

@Entity
data class PopularMovies(
    val results: List<Result>
)

data class Result(
    val id: Int,
    val title: String
)