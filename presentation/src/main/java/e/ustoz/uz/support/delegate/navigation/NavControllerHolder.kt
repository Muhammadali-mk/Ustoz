package e.ustoz.uz.support.delegate.navigation

import androidx.navigation.fragment.NavHostFragment

class NavControllerHolder<Controller: NavControllerDelegate>(val controller: Controller) {

    fun attachNavFragment(fragment: NavHostFragment) =
        controller.attachNavFragment(fragment)

    fun detachNavController() =
        controller.detach()
}