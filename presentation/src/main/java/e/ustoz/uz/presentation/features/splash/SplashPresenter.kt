package e.ustoz.uz.presentation.features.splash

import e.ustoz.data.utils.flow.collect
import e.ustoz.data.utils.flow.flatMapResult
import e.ustoz.domain.interactor.splash.SplashInteractor
import e.ustoz.uz.global.navigation.GlobalNavController
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope
import javax.inject.Inject

@InjectViewState
class SplashPresenter @Inject constructor(
    private val splashInteractor: SplashInteractor,
    private val globalNavController: GlobalNavController
) : MvpPresenter<SplashView>() {

    @Suppress("EXPERIMENTAL_API_USAGE")
    fun getUpdateState() {
        presenterScope.launch {
            flowOf(Unit)
                .debounce(5000)
                .flatMapResult()
                .collect(
                    onStart = { viewState.onLoading() },
                    onSuccess = { viewState.onSuccess(); navigateToNextScreen() },
                    onFailure = { viewState.onFailure(it) }
                )
        }
    }

    private fun navigateToNextScreen() {
       // globalNavController.navigate(SplashFragmentDirections.actionToMainScreen())
    }
}