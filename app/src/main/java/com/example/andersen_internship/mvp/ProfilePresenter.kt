package com.example.andersen_internship.mvp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import com.example.andersen_internship.NetworkService
import com.example.andersen_internship.R
import com.example.andersen_internship.roomdatabase.DatabaseRepository
import com.example.andersen_internship.roomdatabase.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class ProfilePresenter : MvpPresenter<ProfileView>() {

    private var compositeDisposable = CompositeDisposable()
    val movie: ArrayList<Movie> = ArrayList()

    fun loadingMovies(context: Context) {
        compositeDisposable.add(
            NetworkService.buildService().getMovies(context.getString(R.string.api_key))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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

    @SuppressLint("CheckResult")
    fun getMovieDataFromDatabase() {

        for (i in 0..9) {
            movie.add(Movie(i, "collection_$i"))
        }

        DatabaseRepository.addMovies(movie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { viewState.showSuccessMessage() }

        DatabaseRepository.getAllMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { viewState.showDatabaseElementsToast(movie) }
    }

    @SuppressLint("CheckResult")
    fun getMovies() {
        DatabaseRepository.getAllMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { viewState.showDatabaseElementsToast(movie) }
    }
}