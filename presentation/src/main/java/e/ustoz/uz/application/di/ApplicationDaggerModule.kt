package e.ustoz.uz.application.di


import android.content.Context
import dagger.Module
import dagger.Provides
import e.ustoz.data.manager.coroutine.CoroutineContextManager
import e.ustoz.data.provider.DataProvider
import e.ustoz.data.provider.manager.DataManagerProvider
import e.ustoz.data.provider.repository.DataRepositoryProvider
import e.ustoz.data.provider.repository.datasource.DataPreferenceSourceProvider
import e.ustoz.data.repository.course.CourseRepository
import e.ustoz.data.repository.state.StateRepository
import e.ustoz.uz.application.di.ApplicationDaggerModule.Provider
import e.ustoz.uz.application.di.ApplicationDaggerModule.ProviderPreferenceSource
import e.ustoz.uz.application.di.ApplicationDaggerModule.ProviderRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
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

        @JvmStatic
        @Provides
        @Singleton
        fun provideStateRepository(
            dataRepositoryProvider: DataRepositoryProvider
        ): StateRepository = dataRepositoryProvider.stateRepository

        @JvmStatic
        @Provides
        @Singleton
        fun provideCourseRepository(
            dataRepositoryProvider: DataRepositoryProvider
        ): CourseRepository = dataRepositoryProvider.courseRepository
    }
}