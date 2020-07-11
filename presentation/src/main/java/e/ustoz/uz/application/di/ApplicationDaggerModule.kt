package uz.anotomica.app.presentation.application.di


import android.content.Context
import dagger.Module
import dagger.Provides
import e.ustoz.data.manager.coroutine.CoroutineContextManager
import e.ustoz.data.provider.DataProvider
import e.ustoz.data.provider.manager.DataManagerProvider
import e.ustoz.data.provider.repository.DataRepositoryProvider
import e.ustoz.data.provider.repository.datasource.DataPreferenceSourceProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import uz.anotomica.app.presentation.application.di.ApplicationDaggerModule.Provider
import uz.anotomica.app.presentation.application.di.ApplicationDaggerModule.ProviderPreferenceSource
import uz.anotomica.app.presentation.application.di.ApplicationDaggerModule.ProviderRepository
import javax.inject.Singleton

@Module(includes = [Provider::class, ProviderPreferenceSource::class, ProviderRepository::class])
object ApplicationDaggerModule {

    @Module(includes = [ProviderRepository::class])
    object Provider {

        @ExperimentalCoroutinesApi
        val queryEnteredChannel: BroadcastChannel<String> by lazy {
            BroadcastChannel<String>(Channel.CONFLATED)
        }

        @JvmStatic
        @Provides
        @Singleton
        @ExperimentalCoroutinesApi
        fun provideQueryEnteredBroadcastChannel(): BroadcastChannel<String> = queryEnteredChannel

        @JvmStatic
        @Provides
        @Singleton
        fun provideDataProvider(
            context: Context
        ): DataProvider = DataProvider(context)

        @JvmStatic
        @Provides
        @Singleton
        fun provideDataManagerProvider(
            dataProvider: DataProvider
        ): DataManagerProvider = dataProvider.managerProvider

        @JvmStatic
        @Provides
        @Singleton
        fun provideCoroutineContextManager(
            managerProvider: DataManagerProvider
        ): CoroutineContextManager = managerProvider.coroutineContextManager

        @JvmStatic
        @Provides
        @Singleton
        fun provideDataRepositoryProvider(
            dataProvider: DataProvider
        ): DataRepositoryProvider = dataProvider.repositoryProvider

        @JvmStatic
        @Provides
        @Singleton
        fun provideDataPreferenceSourceProvider(
            dataProvider: DataProvider
        ): DataPreferenceSourceProvider = dataProvider.dataPreferenceSourceProvider

    }

    @Module
    object ProviderPreferenceSource {

    }


    @Module
    object ProviderRepository {


    }
}