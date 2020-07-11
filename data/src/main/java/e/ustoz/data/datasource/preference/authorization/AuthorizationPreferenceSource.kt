package uz.anotomica.app.data.datasource.preference.authorization

import e.ustoz.data.manager.preference.PreferenceManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class AuthorizationPreferenceSource(
    private val preferenceManager: PreferenceManager
) {

    val isLoginHasBeen: Boolean
        get() = preferenceManager.getBoolean(PREFERENCE_BOOLEAN_LOGIN_HAS_BEEN_KEY, false)

    val accessToken: String?
        get() = preferenceManager.getString(PREFERENCE_STRING_ACCESS_TOKEN_KEY, "").let {
            return@let if (it.isEmpty()) null
            else "Bearer $it"
        }

    val refreshToken: String?
        get() = preferenceManager.getString(PREFERENCE_STRING_REFRESH_TOKEN_KEY, "")
            .let { if (it.isNotBlank()) it else null }

    fun setLoginHasBeen(value: Boolean) =
        preferenceManager.setBoolean(PREFERENCE_BOOLEAN_LOGIN_HAS_BEEN_KEY, value)

    fun setAccessToken(value: String) {
        preferenceManager.setString(PREFERENCE_STRING_ACCESS_TOKEN_KEY, value)
    }

    fun setRefreshToken(value: String) {
        preferenceManager.setString(PREFERENCE_STRING_REFRESH_TOKEN_KEY, value)
    }

    fun clearAll(): Flow<Unit> =
        flowOf(preferenceManager.clearAll())

    private companion object {
        const val PREFERENCE_BOOLEAN_LOGIN_HAS_BEEN_KEY: String =
            "preference_boolean_is_login_has_been"
        const val PREFERENCE_STRING_ACCESS_TOKEN_KEY: String = "preference_string_access_token"
        const val PREFERENCE_STRING_REFRESH_TOKEN_KEY: String = "preference_string_refresh_token"
    }
}