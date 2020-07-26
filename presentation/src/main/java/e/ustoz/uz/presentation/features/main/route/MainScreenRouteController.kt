package e.ustoz.uz.presentation.features.main.route

class MainScreenRouteController {

    private var eventListener: RouteEventListener? = null
    private var currentRouteItem = MainScreenRouteItem.COURSE_ITEM


    fun setEventListener(eventListener: RouteEventListener?) {
        this.eventListener = eventListener
    }

    fun navigateToUserInfoScreen() {
        eventListener?.onStartNavigateToUserInfo()
    }

    fun navigateToMaterials() {
        eventListener?.onStartNavigateToMaterialsScreen()
    }

    fun navigateToDaybookScreen() {
        eventListener?.onStartNavigateToDaybookScreen()
    }

    fun navigateToSearchScreen() {
        eventListener?.onStartNavigateToSearchScreen()
    }

    fun navigateToTab(routeItem: MainScreenRouteItem) {
        currentRouteItem = routeItem
        eventListener?.onStartNavigateToTab(routeItem)
    }

    interface RouteEventListener {
        fun onStartNavigateToUserInfo()

        fun onStartNavigateToMaterialsScreen()

        fun onStartNavigateToDaybookScreen()

        fun onStartNavigateToSearchScreen()

        fun onStartNavigateToTab(routeItem: MainScreenRouteItem)
    }
}