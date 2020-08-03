package com.example.andersen_internship.mvp

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import com.example.andersen_internship.MyNotification
import com.example.andersen_internship.NetworkService
import com.example.andersen_internship.PopularMovies
import com.example.andersen_internship.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class ProfilePresenter : MvpPresenter<ProfileView>() {

    private val compositeDisposable = CompositeDisposable()
    var myNotification = MyNotification.instance

    fun onRetrofitRequestClick(context: Context) {
        compositeDisposable.add(
            NetworkService.buildService().getMovies(context.getString(R.string.api_key))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ response -> onResponse(response, context) }, { t -> onFailure(t, context) })
        )
    }

    private fun onFailure(t: Throwable, context: Context) {
        Toast.makeText(context, "onFailure", Toast.LENGTH_SHORT).show()
    }

    private fun onResponse(response: PopularMovies, context: Context) {
        Toast.makeText(context, "Success request", Toast.LENGTH_SHORT).show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onNotificationClick(activity: FragmentActivity) {
        myNotification.notification(activity)
    }
}