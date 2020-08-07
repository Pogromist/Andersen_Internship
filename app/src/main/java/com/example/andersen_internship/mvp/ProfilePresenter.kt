package com.example.andersen_internship.mvp

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import com.example.andersen_internship.NetworkService
import com.example.andersen_internship.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class ProfilePresenter : MvpPresenter<ProfileView>() {

    private var compositeDisposable = CompositeDisposable()

    fun loadingMovies(context: Context) {
        compositeDisposable.add(
            NetworkService.buildService().getMovies(context.getString(R.string.api_key))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ data -> viewState.showMovies(data) }, { viewState.onFailure() })
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onNotificationClick(fragmentActivity: FragmentActivity) {
        viewState.showNotification(fragmentActivity)
    }
}