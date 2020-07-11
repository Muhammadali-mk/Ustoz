package e.ustoz.data.provider

import android.content.Context
import e.ustoz.data.provider.manager.DataManagerProvider
import e.ustoz.data.provider.repository.DataRepositoryProvider
import e.ustoz.data.provider.repository.DataRepositoryProviderImpl
import e.ustoz.data.provider.repository.datasource.DataPreferenceSourceProvider
import e.ustoz.data.provider.repository.datasource.DataPreferenceSourceProviderImpl

class DataProvider(context: Context) {

    val managerProvider by lazy { DataManagerProvider(context) }

    val repositoryProvider: DataRepositoryProvider by lazy {
        DataRepositoryProviderImpl(
            context = context
        )
    }

    val dataPreferenceSourceProvider: DataPreferenceSourceProvider by lazy {
        DataPreferenceSourceProviderImpl(context)
    }
}