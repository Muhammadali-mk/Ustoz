package uz.anotomica.app.data.model.language

import java.util.*

enum class Language(internal val language: String) {
    RUSSIAN(language = "ru"),
    ENGLISH(language = "en"),
    UZBEK(language = "uz");

    var isSelected: Boolean = false; internal set

    val locale: Locale
        get() = Locale(language)

    val languageName: String
        get() = locale.getDisplayName(locale)
            .let { it.substring(0, 1).toUpperCase(locale).plus(it.substring(1)) }

}