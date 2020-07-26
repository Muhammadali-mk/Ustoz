package e.ustoz.data.repository.state

import e.ustoz.data.datasource.preference.authorization.AuthorizationPreferenceSource
import e.ustoz.data.datasource.preference.state.StatePreferenceSource

internal class StateRepositoryImpl (
    private val authorizationPreferenceSource: AuthorizationPreferenceSource,
    private val statePreferenceSource: StatePreferenceSource
) : StateRepository {

    override fun isActivated(): Boolean =
        authorizationPreferenceSource.isLoginHasBeen

    override fun isFirstTime(): Boolean {
        val isFirstTime: Boolean = statePreferenceSource.isFirstTime
        if (isFirstTime) statePreferenceSource.setFirstTime(false)
        return isFirstTime
    }
}