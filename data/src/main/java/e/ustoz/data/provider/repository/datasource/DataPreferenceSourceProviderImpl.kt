package e.ustoz.data.provider.repository.datasource

import android.content.Context
import e.ustoz.data.datasource.preference.PreferenceSource
import e.ustoz.data.datasource.preference.language.LanguagePreferenceSource
import e.ustoz.data.manager.preference.PreferenceManager
import e.ustoz.data.manager.preference.PreferenceManagerImpl

internal class DataPreferenceSourceProviderImpl(private val context: Context) :
    DataPreferenceSourceProvider() {
    private val preferenceSource: PreferenceSource = PreferenceSource { getPreferenceManager(it) }

    override val languagePreferenceSource: LanguagePreferenceSource
        get() = preferenceSource.languagePreferenceSource()


    private fun getPreferenceManager(preferenceName: String): PreferenceManager =
        PreferenceManagerImpl(context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE))
}