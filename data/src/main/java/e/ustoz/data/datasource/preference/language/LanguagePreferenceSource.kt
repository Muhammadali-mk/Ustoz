package e.ustoz.data.datasource.preference.language

import e.ustoz.data.manager.preference.PreferenceManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import uz.anotomica.app.data.model.language.Language

class LanguagePreferenceSource internal constructor(
    private val preferenceManager: PreferenceManager
) {

    val isLanguageSelected: Boolean
        get() = preferenceManager.getBoolean(PREFERENCE_BOOLEAN_IS_SELECTED, false)

    val currentLanguage: Language
        get() = preferenceManager.getString(
            PREFERENCE_STRING_SELECTED_LANGUAGE,
            Language.UZBEK.language
        ).let { if (Language.UZBEK.language == it) Language.UZBEK else Language.RUSSIAN }

    fun setLanguage(language: Language) {
        preferenceManager.apply {
            setString(PREFERENCE_STRING_SELECTED_LANGUAGE, language.language)
            setBoolean(PREFERENCE_BOOLEAN_IS_SELECTED, true)
        }
    }

    fun clearAll(): Flow<Unit> =
        flowOf(preferenceManager.clearAll())

    companion object {
        const val PREFERENCE_BOOLEAN_IS_SELECTED = "preference_boolean_is_selected"
        const val PREFERENCE_STRING_SELECTED_LANGUAGE = "preference_string_language"
    }
}