package e.ustoz.data.provider.repository.datasource

import e.ustoz.data.datasource.preference.language.LanguagePreferenceSource

abstract class DataPreferenceSourceProvider {

    abstract val languagePreferenceSource: LanguagePreferenceSource

}