package uz.anotomica.app.presentation.presentation.features.splash

import moxy.MvpView

interface SplashView : MvpView {

    fun onLoading()

    fun onSuccess()

    fun onFailure(throwable: Throwable)
}