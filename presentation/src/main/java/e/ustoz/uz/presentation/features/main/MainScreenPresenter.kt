package e.ustoz.uz.presentation.features.main

import e.ustoz.domain.interactor.main.MainScreenInteractor
import e.ustoz.uz.global.navigation.GlobalNavController
import e.ustoz.uz.presentation.features.main.route.MainScreenRouteController
import e.ustoz.uz.presentation.features.main.route.MainScreenRouteItem
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class MainScreenPresenter @Inject constructor(
    private val mainScreenInteractor: MainScreenInteractor,
    private val mainScreenRouteController: MainScreenRouteController,
    private val globalNavController: GlobalNavController
) : MvpPresenter<MainScreenView>() {

    override fun onFirstViewAttach() {

        mainScreenRouteController.setEventListener(object :
            MainScreenRouteController.RouteEventListener {

            override fun onStartNavigateToUserInfo() {
                globalNavController.navigate(MainScreenFragmentDirections.actionToUserInfoScreen())
            }

            override fun onStartNavigateToMaterialsScreen() {
            }

            override fun onStartNavigateToDaybookScreen() {
            }

            override fun onStartNavigateToSearchScreen() {
            }

            override fun onStartNavigateToTab(routeItem: MainScreenRouteItem) {
            }
        })
    }

}