package e.ustoz.uz.presentation.features.main.features.material

import e.ustoz.uz.presentation.features.main.route.MainScreenRouteController
import moxy.MvpPresenter
import javax.inject.Inject

class MaterialsPresenter @Inject constructor(
    private val mainScreenRouteController: MainScreenRouteController
): MvpPresenter<MaterialsView>() {
}