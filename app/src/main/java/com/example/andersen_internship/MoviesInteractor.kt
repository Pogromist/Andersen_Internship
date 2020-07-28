package com.example.andersen_internship

import android.content.Context
import retrofit2.Call

class MoviesInteractor private constructor(){

    private object HOLDER {
        val INSTANCE = MoviesInteractor()
    }

    companion object {
        val instance: MoviesInteractor by lazy { HOLDER.INSTANCE }
    }

    fun getMovies(context: Context): Call<PopularMovies>? {
        return NetworkService.instance.Api()
            ?.getMovies(context.getString(R.string.api_key))
    }
}