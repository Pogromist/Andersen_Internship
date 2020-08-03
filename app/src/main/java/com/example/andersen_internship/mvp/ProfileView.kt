package com.example.andersen_internship.mvp

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface ProfileView : MvpView {
    fun retrofitRequest()
    fun notification()
}
