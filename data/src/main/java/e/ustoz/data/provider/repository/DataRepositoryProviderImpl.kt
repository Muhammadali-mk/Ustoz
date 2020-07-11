package e.ustoz.data.provider.repository

import android.content.Context
import e.ustoz.data.provider.repository.datasource.DataDatabaseProvider
import e.ustoz.data.provider.repository.datasource.DataPreferenceSourceProvider
import e.ustoz.data.provider.repository.datasource.DataPreferenceSourceProviderImpl


internal class DataRepositoryProviderImpl(context: Context) : DataRepositoryProvider {

    private val dataDatabaseProvider: DataDatabaseProvider by lazy {
        DataDatabaseProvider(context)
    }


    private val dataPreferenceSourceProvider: DataPreferenceSourceProvider by lazy {
        DataPreferenceSourceProviderImpl(context)
    }
}