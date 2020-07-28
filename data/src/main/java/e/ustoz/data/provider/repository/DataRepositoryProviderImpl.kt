package e.ustoz.data.provider.repository

import android.content.Context
import e.ustoz.data.provider.repository.datasource.DataDatabaseProvider
import e.ustoz.data.provider.repository.datasource.DataPreferenceSourceProvider
import e.ustoz.data.provider.repository.datasource.DataPreferenceSourceProviderImpl
import e.ustoz.data.repository.course.CourseRepository
import e.ustoz.data.repository.course.CourseRepositoryImpl
import e.ustoz.data.repository.state.StateRepository
import e.ustoz.data.repository.state.StateRepositoryImpl


internal class DataRepositoryProviderImpl(context: Context) : DataRepositoryProvider {

    private val dataDatabaseProvider: DataDatabaseProvider by lazy {
        DataDatabaseProvider(context)
    }


    private val dataPreferenceSourceProvider: DataPreferenceSourceProvider by lazy {
        DataPreferenceSourceProviderImpl(context)
    }
    override val stateRepository: StateRepository
        =StateRepositoryImpl(
        authorizationPreferenceSource = dataPreferenceSourceProvider.authorizationPreferenceSource,
        statePreferenceSource = dataPreferenceSourceProvider.statePreferenceSource
    )
    override val courseRepository: CourseRepository=CourseRepositoryImpl()
}