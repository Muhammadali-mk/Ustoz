package e.ustoz.data.datasource.preference

import e.ustoz.data.datasource.preference.language.LanguagePreferenceSource
import e.ustoz.data.manager.preference.PreferenceManager

internal class PreferenceSource(
    private val preferenceManager: (preferenceName: String) -> PreferenceManager
) {

    fun languagePreferenceSource() =
        LanguagePreferenceSource(preferenceManager.invoke(LANGUAGE_PREFERENCE_SOURCE_NAME))

    private companion object {
        const val LANGUAGE_PREFERENCE_SOURCE_NAME: String = "preference_language"
    }
}