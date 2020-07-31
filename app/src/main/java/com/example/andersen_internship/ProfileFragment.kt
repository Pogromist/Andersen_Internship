package com.example.andersen_internship

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    lateinit var myNotification: MyNotification
    val compositeDisposable = CompositeDisposable()

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
            compositeDisposable.add(
                NetworkService.buildService().getMovies(getString(R.string.api_key))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ response -> onResponse(response) }, { t -> onFailure(t) })
            )
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.dispose()
    }

    private fun onFailure(t: Throwable) {
        Toast.makeText(context, "onFailure", Toast.LENGTH_SHORT).show()
    }

    private fun onResponse(response: PopularMovies) {
        Toast.makeText(context, "Success request", Toast.LENGTH_SHORT).show()
    }
}





