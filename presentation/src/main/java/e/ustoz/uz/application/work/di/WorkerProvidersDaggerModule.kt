package uz.anotomica.app.presentation.application.work.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import e.ustoz.uz.support.dagger.worker.WorkerKey
import e.ustoz.uz.support.dagger.worker.WorkerProvider
import uz.anotomica.app.presentation.application.work.di.WorkerProvidersDaggerModule.Binder
import uz.anotomica.app.presentation.application.work.worker.SyncWorker

@Module(includes = [Binder::class])
object WorkerProvidersDaggerModule {

    @Module
    interface Binder {

        @Binds
        @IntoMap
        @WorkerKey(SyncWorker::class)
        fun bindSyncWorkerProvider(provider: SyncWorker.Provider): WorkerProvider
    }
}