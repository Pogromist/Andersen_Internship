package com.example.andersen_internship.roomdatabase

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {

    val compositeDisposable = CompositeDisposable()
    private var dataBaseInstance: MovieDatabase ?= null
    var moviesList = MutableLiveData<List<MovieData>>()

    fun setInstanceOfDb(dataBaseInstance: MovieDatabase) {
        this.dataBaseInstance = dataBaseInstance
    }

    fun saveDataIntoDb(data: MovieData){

        dataBaseInstance?.movieDataDao()?.insertMovieData(data)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe ({
            },{

            })?.let {
                compositeDisposable.add(it)
            }
    }

    fun getMovieData(){

        dataBaseInstance?.movieDataDao()?.getAllRecords()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe ({
                if(!it.isNullOrEmpty()){
                    moviesList.postValue(it)
                }else{
                    moviesList.postValue(listOf())
                }
                it?.forEach {
                    Log.v("Person Name",it.title)
                }
            },{
            })?.let {
                compositeDisposable.add(it)
            }
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        compositeDisposable.clear()
        super.onCleared()
    }

    fun deleteMovie(movie: MovieData) {
        dataBaseInstance?.movieDataDao()?.deleteMovieData(movie)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe ({
                //Refresh Page data
                getMovieData()
            },{

            })?.let {
                compositeDisposable.add(it)
            }
    }
}