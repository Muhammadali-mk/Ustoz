package e.ustoz.domain.interactor.sync

import e.ustoz.data.manager.coroutine.CoroutineContextManager
import e.ustoz.data.utils.flow.flatMapResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SyncInteractor @Inject constructor(
    private val coroutineContextManager: CoroutineContextManager
) {

    @FlowPreview
    @ExperimentalCoroutinesApi
    fun syncData(): Flow<Result<Unit>> {
        return flowOf(Unit)
            .flatMapResult()
            .flowOn(coroutineContextManager.ioContext)
    }
}