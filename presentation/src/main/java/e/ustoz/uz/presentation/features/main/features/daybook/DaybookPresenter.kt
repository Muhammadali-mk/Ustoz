package e.ustoz.uz.presentation.features.main.features.daybook

import e.ustoz.uz.presentation.features.main.route.MainScreenRouteController
import moxy.MvpPresenter
import javax.inject.Inject

class DaybookPresenter @Inject constructor(
    private val mainScreenRouteController: MainScreenRouteController
): MvpPresenter<DaybookView>() {
}