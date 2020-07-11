package e.ustoz.data.datasource.preference.state

import e.ustoz.data.manager.preference.PreferenceManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class StatePreferenceSource(
    private val preferenceManager: PreferenceManager
) {

    val isFirstTime: Boolean
        get() = preferenceManager.getBoolean(PREFERENCE_BOOLEAN_FIRST_TIME_KEY, false)


    fun setFirstTime(value: Boolean) =
        preferenceManager.setBoolean(PREFERENCE_BOOLEAN_FIRST_TIME_KEY, value)


    fun clearAll(): Flow<Unit> =
        flowOf(preferenceManager.clearAll())

    private companion object {
        const val PREFERENCE_BOOLEAN_FIRST_TIME_KEY: String = "preference_boolean_is_first time_been"
    }
}