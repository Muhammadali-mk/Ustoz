package e.ustoz.data.provider.repository.datasource

import e.ustoz.data.datasource.preference.authorization.AuthorizationPreferenceSource
import e.ustoz.data.datasource.preference.language.LanguagePreferenceSource
import e.ustoz.data.datasource.preference.state.StatePreferenceSource

abstract class DataPreferenceSourceProvider {

    internal abstract val authorizationPreferenceSource: AuthorizationPreferenceSource


    abstract val languagePreferenceSource: LanguagePreferenceSource

    internal abstract val statePreferenceSource: StatePreferenceSource

}