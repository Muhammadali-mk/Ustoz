package e.ustoz.uz.presentation.features.main.features.test

import e.ustoz.uz.presentation.features.main.route.MainScreenRouteController
import moxy.MvpPresenter
import javax.inject.Inject

class TestsPresenter @Inject constructor(
    private val mainScreenRouteController: MainScreenRouteController
): MvpPresenter<TestsView>() {
}