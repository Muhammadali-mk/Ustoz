package e.ustoz.uz.presentation.features.splash

import moxy.MvpView

interface SplashView : MvpView {

    fun onLoading()

    fun onSuccess()

    fun onFailure(throwable: Throwable)
}