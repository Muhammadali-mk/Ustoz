package uz.anotomica.app.presentation.presentation.features.main.route

class MainScreenRouteController {

    private var eventListener: RouteEventListener? = null
    private var currentRouteItem = MainScreenRouteItem.HOME_ITEM


    fun setEventListener(eventListener: RouteEventListener?) {
        this.eventListener = eventListener
    }

    fun navigateToAboutUsScreen() {
        eventListener?.onStartNavigateToAboutUsScreen()
    }

    fun navigateToAuthScreen() {
        eventListener?.onStartNavigateToAuthScreen()
    }

    fun navigateToHeadingPostScreen(posts: String) {
        eventListener?.onStartNavigateToHeadingPostScreen(posts)
    }

    fun navigateToPostDetailScreen(postId: String) {
        eventListener?.onStartNavigateToPostDetailScreen(postId)
    }

    fun navigateToSearchScreen() {
        eventListener?.onStartNavigateToSearchScreen()
    }

    fun navigateToTab(routeItem: MainScreenRouteItem) {
        currentRouteItem = routeItem
        eventListener?.onStartNavigateToTab(routeItem)
    }

    interface RouteEventListener {
        fun onStartNavigateToAboutUsScreen()

        fun onStartNavigateToAuthScreen()

        fun onStartNavigateToHeadingPostScreen(posts: String)

        fun onStartNavigateToPostDetailScreen(postId: String)

        fun onStartNavigateToSearchScreen()

        fun onStartNavigateToTab(routeItem: MainScreenRouteItem)
    }
}