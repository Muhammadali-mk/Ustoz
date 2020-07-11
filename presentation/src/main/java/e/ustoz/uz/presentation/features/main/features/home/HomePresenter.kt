package e.ustoz.uz.presentation.features.main.features.home

import moxy.InjectViewState
import moxy.MvpPresenter
import uz.anotomica.app.presentation.presentation.features.main.route.MainScreenRouteController
import javax.inject.Inject

@InjectViewState
class HomePresenter @Inject constructor(
    private val mainScreenRouteController: MainScreenRouteController
) : MvpPresenter<HomeView>() {


}

