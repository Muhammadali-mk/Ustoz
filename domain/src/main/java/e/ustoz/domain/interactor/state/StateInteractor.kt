package e.ustoz.domain.interactor.state

import e.ustoz.data.manager.coroutine.CoroutineContextManager
import e.ustoz.data.repository.state.StateRepository
import javax.inject.Inject

class StateInteractor @Inject constructor(
    private val stateRepository: StateRepository,
    private val coroutineContextManager: CoroutineContextManager
) {

    fun isFirstTime(): Boolean =
        stateRepository.isFirstTime()

    fun isActivated(): Boolean =
        stateRepository.isActivated()
}