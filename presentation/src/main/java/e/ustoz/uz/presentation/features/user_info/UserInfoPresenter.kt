package e.ustoz.uz.presentation.features.user_info

import e.ustoz.uz.presentation.features.main.route.MainScreenRouteController
import e.ustoz.uz.presentation.features.main.route.MainScreenRouteItem
import moxy.MvpPresenter
import javax.inject.Inject

class UserInfoPresenter @Inject constructor(
    private val routeController: MainScreenRouteController
):MvpPresenter<UserInfoView>() {

    fun backToMain() =
        routeController.navigateToTab(MainScreenRouteItem.COURSE_ITEM)
}