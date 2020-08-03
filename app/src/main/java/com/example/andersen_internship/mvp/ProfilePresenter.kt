package com.example.andersen_internship.mvp

import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class ProfilePresenter : MvpPresenter<ProfileView>() {
    fun onRetrofitRequestClick() {
        viewState.retrofitRequest()
    }
    fun onNotificationClick() {
        viewState.notification()
    }
}