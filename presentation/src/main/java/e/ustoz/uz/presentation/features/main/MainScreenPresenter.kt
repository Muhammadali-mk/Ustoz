package uz.anotomica.app.presentation.presentation.features.main

import e.ustoz.uz.global.navigation.GlobalNavController
import moxy.InjectViewState
import moxy.MvpPresenter
import uz.anotomica.app.domain.interactor.main.MainScreenInteractor
import uz.anotomica.app.presentation.presentation.features.main.route.MainScreenRouteController
import uz.anotomica.app.presentation.presentation.features.main.route.MainScreenRouteItem
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
            override fun onStartNavigateToAboutUsScreen() {

            }

            override fun onStartNavigateToAuthScreen() {
                globalNavController.navigate(
                    MainScreenFragmentDirections.actionToAuthScreen()
                )
            }

            override fun onStartNavigateToHeadingPostScreen(posts: String) {
                globalNavController
                    .navigate(MainScreenFragmentDirections.actionToHeadingPostsScreen(posts))
            }

            override fun onStartNavigateToPostDetailScreen(post: String) {
                globalNavController
                    .navigate(MainScreenFragmentDirections.actionToPostDetailScreen(post))
            }

            override fun onStartNavigateToSearchScreen() {
                globalNavController
                    .navigate(MainScreenFragmentDirections.actionToSearchScreen())
            }

            override fun onStartNavigateToTab(routeItem: MainScreenRouteItem) {

            }
        })
    }

}