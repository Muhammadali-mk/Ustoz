package e.ustoz.uz.application.work.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import e.ustoz.uz.application.work.di.WorkerProvidersDaggerModule.Binder
import e.ustoz.uz.application.work.worker.SyncWorker
import e.ustoz.uz.support.dagger.worker.WorkerKey
import e.ustoz.uz.support.dagger.worker.WorkerProvider

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