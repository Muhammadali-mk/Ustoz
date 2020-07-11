package e.ustoz.domain.interactor.splash

import e.ustoz.data.manager.coroutine.CoroutineContextManager
import e.ustoz.data.utils.flow.flatMapResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SplashInteractor @Inject constructor(
    private val coroutineContextManager: CoroutineContextManager
) {

    @ExperimentalCoroutinesApi
    fun getUpdateState(): Flow<Result<Long>> {
        return flowOf(0L)
            .flatMapResult()
            .flowOn(coroutineContextManager.ioContext)
    }

    @ExperimentalCoroutinesApi
    fun getChanges(): Flow<Result<Unit>> {
        return flowOf(Unit)
            .flatMapResult()
            .flowOn(coroutineContextManager.ioContext)
    }
}