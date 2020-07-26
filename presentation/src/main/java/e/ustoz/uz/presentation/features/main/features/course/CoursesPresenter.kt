package e.ustoz.uz.presentation.features.main.features.course

import e.ustoz.uz.presentation.features.main.route.MainScreenRouteController
import moxy.MvpPresenter
import javax.inject.Inject

class CoursesPresenter @Inject constructor(
    private val mainScreenRouteController: MainScreenRouteController
) : MvpPresenter<CoursesView>() {

    fun navigateToUserInfo() {
        mainScreenRouteController.navigateToUserInfoScreen()
    }


}
