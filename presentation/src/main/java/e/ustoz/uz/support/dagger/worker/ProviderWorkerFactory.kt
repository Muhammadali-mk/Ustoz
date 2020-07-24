package uz.anotomica.app.presentation.support.dagger.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import e.ustoz.uz.support.dagger.worker.WorkerProvider
import javax.inject.Inject
import javax.inject.Provider

class ProviderWorkerFactory @Inject constructor(
    private val providersMap: Map<Class<out ListenableWorker>, @JvmSuppressWildcards Provider<WorkerProvider>>
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        val entry: Map.Entry<Class<out ListenableWorker>, @JvmSuppressWildcards Provider<WorkerProvider>>? =
            providersMap.entries.find { Class.forName(workerClassName).isAssignableFrom(it.key) }
        val provider: Provider<WorkerProvider> =
            entry?.value ?: throw IllegalArgumentException("unknown worker class name: $workerClassName")
        return provider.get().provideWorker(appContext, workerParameters)
    }
}