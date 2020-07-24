package e.ustoz.uz.application.work.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import com.google.common.util.concurrent.ListenableFuture
import e.ustoz.data.manager.coroutine.CoroutineContextManager
import e.ustoz.domain.interactor.sync.SyncInteractor
import e.ustoz.uz.support.dagger.worker.WorkerProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SyncWorker(
    private val syncInteractor: SyncInteractor,
    coroutineContextManager: CoroutineContextManager,
    context: Context,
    workerParams: WorkerParameters
) : ListenableWorker(context, workerParams) {
    private val serviceJob = CoroutineScope(context = coroutineContextManager.mainContext + Job())

    override fun startWork(): ListenableFuture<Result> =
        coroutineWorker.startWork()

    override fun onStopped() {
        coroutineWorker.onStopped()
        serviceJob.coroutineContext.cancel()
    }

    @Suppress("EXPERIMENTAL_API_USAGE")
    private val coroutineWorker: CoroutineWorker = object : CoroutineWorker(context, workerParams) {
        override suspend fun doWork(): Result {
            return withContext(serviceJob.coroutineContext) {
                syncInteractor.syncData()
                    .map { Result.success() }
                    .first()
            }
        }
    }

    class Provider @Inject constructor(
        private val syncInteractor: SyncInteractor,
        private val coroutineContextManager: CoroutineContextManager
    ): WorkerProvider {
        override fun provideWorker(context: Context, workerParams: WorkerParameters) =
            SyncWorker(
                syncInteractor = syncInteractor,
                coroutineContextManager = coroutineContextManager,
                context = context,
                workerParams = workerParams
            )
    }
}