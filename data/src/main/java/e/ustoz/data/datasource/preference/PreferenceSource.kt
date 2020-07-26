package e.ustoz.data.datasource.preference

import e.ustoz.data.datasource.preference.authorization.AuthorizationPreferenceSource
import e.ustoz.data.datasource.preference.language.LanguagePreferenceSource
import e.ustoz.data.datasource.preference.state.StatePreferenceSource
import e.ustoz.data.manager.preference.PreferenceManager

internal class PreferenceSource(
    private val preferenceManager: (preferenceName: String) -> PreferenceManager
) {
    fun authorizationPreferenceSource() =
        AuthorizationPreferenceSource(preferenceManager.invoke(AUTHORIZATION_PREFERENCE_SOURCE_NAME))

    fun languagePreferenceSource() =
        LanguagePreferenceSource(preferenceManager.invoke(LANGUAGE_PREFERENCE_SOURCE_NAME))

    fun statePreferenceSource() =
        StatePreferenceSource(preferenceManager.invoke(LANGUAGE_PREFERENCE_SOURCE_NAME))

    private companion object {
        const val AUTHORIZATION_PREFERENCE_SOURCE_NAME: String = "preference_authorization"
        const val LANGUAGE_PREFERENCE_SOURCE_NAME: String = "preference_language"
        const val STATE_PREFERENCE_SOURCE_NAME: String = "preference_state"
    }
}