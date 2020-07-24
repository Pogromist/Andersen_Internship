package com.example.andersen_internship

data class PopularMovies (
    val results: List<Result>
)
data class Result(
    val id: Int,
    val title: String,
    val overview: String
)