package com.example.andersen_internship.mvp

import androidx.fragment.app.FragmentActivity
import com.example.andersen_internship.PopularMovies
import com.example.andersen_internship.roomdatabase.Movie
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface ProfileView : MvpView {
    fun showMovies(data: PopularMovies)
    fun onFailure()
    fun showNotification(activity: FragmentActivity)
    fun showDatabaseElementsToast(data: ArrayList<Movie>)
    fun showSuccessMessage()
}
