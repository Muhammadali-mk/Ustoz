package uz.anotomica.app.presentation.application.work.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import com.google.common.util.concurrent.ListenableFuture
import e.ustoz.uz.support.dagger.worker.WorkerProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.withContext
import uz.anotomica.app.data.manager.coroutine.CoroutineContextManager
import uz.anotomica.app.domain.interactor.sync.SyncInteractor
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