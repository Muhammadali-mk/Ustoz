package e.ustoz.uz.application.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import e.ustoz.data.manager.coroutine.CoroutineContextManager
import e.ustoz.data.provider.repository.DataRepositoryProvider
import e.ustoz.uz.application.work.di.WorkerProvidersDaggerModule
import kotlinx.coroutines.channels.BroadcastChannel
import uz.anotomica.app.data.manager.coroutine.CoroutineContextManager
import uz.anotomica.app.data.provider.repository.DataRepositoryProvider
import uz.anotomica.app.presentation.application.Application
import uz.anotomica.app.presentation.application.di.ApplicationDaggerModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationDaggerModule::class, WorkerProvidersDaggerModule::class])
interface ApplicationDaggerComponent : DataRepositoryProvider {

//    fun appLocaleManager(): AppLocaleManager

    @Suppress("EXPERIMENTAL_API_USAGE")
    fun queryEnteredChannel(): BroadcastChannel<String>

    fun coroutineContextManager(): CoroutineContextManager

    fun inject(application: Application)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationDaggerComponent
    }

    companion object {
        fun create(context: Context): ApplicationDaggerComponent =
            DaggerApplicationDaggerComponent
                .factory()
                .create(context)
    }
}