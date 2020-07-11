package e.ustoz.uz.presentation.features.login

import e.ustoz.uz.global.navigation.GlobalNavController
import moxy.InjectViewState
import moxy.MvpPresenter
import uz.anotomica.app.presentation.presentation.features.login.AuthenticateView
import javax.inject.Inject

@InjectViewState
class LoginPresenter @Inject constructor(
    private val globalNavController: GlobalNavController
) : MvpPresenter<AuthenticateView>() {

}