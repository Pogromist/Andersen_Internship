package com.example.andersen_internship.mvp

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
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
    lateinit var data: PopularMovies

    fun loadMovies(context: Context) {
        compositeDisposable.add(
            NetworkService.buildService().getMovies(context.getString(R.string.api_key))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({data -> viewState.loadingMovies(data) }, { viewState.onFailure() })
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onNotificationClick(fragmentActivity: FragmentActivity) {
        viewState.showNotification(fragmentActivity)
    }
}