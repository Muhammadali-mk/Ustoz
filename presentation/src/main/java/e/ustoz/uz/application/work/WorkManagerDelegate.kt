package uz.anotomica.app.presentation.application.work

import android.content.Context
import androidx.work.*
import uz.anotomica.app.presentation.application.work.worker.SyncWorker
import java.util.concurrent.TimeUnit

class WorkManagerDelegate {

    fun enqueue(context: Context) {
        WorkManager.getInstance(context).apply {
            enqueueUniquePeriodicWork(
                SYNC_WORKER_TAG,
                ExistingPeriodicWorkPolicy.REPLACE,
                syncWorkRequest
            )
        }
    }

    private val syncWorkRequest =
        PeriodicWorkRequest.Builder(SyncWorker::class.java, 15, TimeUnit.MINUTES)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .setBackoffCriteria(BackoffPolicy.LINEAR, 15, TimeUnit.MINUTES)
            .build()

    private companion object {
        const val STATE_WORKER_TAG: String = "state_worker"
        const val SYNC_WORKER_TAG: String = "sync_worker"
    }
}