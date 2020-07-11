package uz.anotomica.app.domain.interactor.utils

import java.util.regex.Pattern

internal object Validations {

    fun isPhoneNumberValid(value: String?): Boolean =
        value?.let { Pattern.compile("^(?:[0-9] ?){11,14}[0-9]$").matcher(value).matches() }
            ?: false

    fun isEmailValid(value: String?): Boolean =
        value?.let { android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches() }
            ?: false

    fun isTinValid(value: String?): Boolean =
        value?.length == 9

    fun isPasswordValid(value: String?): Boolean =
        value?.let { Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$").matcher(value).matches() } ?: false
}