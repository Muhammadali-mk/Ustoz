package uz.anotomica.app.presentation.presentation.features.login

import moxy.MvpView

interface AuthenticateView : MvpView {

    fun onLoadingLogin()

    fun onSuccessLogin()

    fun onFailureLogin(throwable: Throwable)

}