package com.example.andersen_internship

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {

    lateinit var myNotification: MyNotification

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        myNotification = MyNotification.instance
        btnShowNotification.setOnClickListener {
            myNotification.notification(requireActivity())
        }

        btnRetrofitRequest.setOnClickListener {

            val request = ServiceBuilder.buildService(TmdbEndpoints::class.java)
            val call = request.getMovies(getString(R.string.api_key))

            call.enqueue(object : Callback<PopularMovies>{
                override fun onFailure(call: Call<PopularMovies>, t: Throwable) {
                    Toast.makeText(context, "onFailure", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<PopularMovies>, response: Response<PopularMovies>) {
                    if (response.isSuccessful) {
                        Toast.makeText(context, "Success request", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }
}



