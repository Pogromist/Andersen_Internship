package com.example.andersen_internship

import android.content.Context
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun createPost(context: Context) {
    NetworkService.instance.Api()
        ?.getMovies(context.getString(R.string.api_key))
        ?.enqueue(object : Callback<PopularMovies> {
            override fun onFailure(call: Call<PopularMovies>, t: Throwable) {
                Toast.makeText(context, "onFailure", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<PopularMovies>,
                response: Response<PopularMovies>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(context, "Success request", Toast.LENGTH_SHORT).show()
                }
            }
        })
}