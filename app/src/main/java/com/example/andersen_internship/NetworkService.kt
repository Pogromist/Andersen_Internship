package com.example.andersen_internship

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService private constructor(){

    private object HOLDER {
        val INSTANCE = NetworkService()
    }

    companion object {
        val instance: NetworkService by lazy { HOLDER.INSTANCE }
        const val BASE_URL = "https://api.themoviedb.org/"
    }

    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun Api(): TmdbEndpoints {
        return retrofit.create(TmdbEndpoints::class.java)
    }
}