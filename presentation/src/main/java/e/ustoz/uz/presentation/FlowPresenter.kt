package e.ustoz.uz.presentation

import e.ustoz.domain.interactor.state.StateInteractor
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class FlowPresenter @Inject constructor(
    private val stateInteractor: StateInteractor
) : MvpPresenter<FlowView>() {

//    override fun onFirstViewAttach() =
//        attachAndInit()

    fun attachAndInit() {
        with(viewState) { onAttachGlobalNavController(); onInitializeNavController(stateInteractor.isActivated()) }
    }
}