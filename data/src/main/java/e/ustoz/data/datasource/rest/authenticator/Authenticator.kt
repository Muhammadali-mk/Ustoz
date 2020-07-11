package e.ustoz.data.datasource.rest.authenticator

import android.util.Log
import e.ustoz.data.BuildConfig
import e.ustoz.data.datasource.rest.constants.ConstantsApiKey
import e.ustoz.data.datasource.rest.service.holder.RestServicesHolder
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import okhttp3.internal.platform.Platform
import okhttp3.internal.platform.Platform.WARN
import uz.anotomica.app.data.datasource.preference.authorization.AuthorizationPreferenceSource

internal class Authenticator(
    private val restServicesHolder: RestServicesHolder,
    private val authorizationPreferenceSource: AuthorizationPreferenceSource
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val isRequestAllowed: Boolean = response.request().header("Authorization") != null

        if (!isRequestAllowed) log("!---- Start Authenticator ----!")
        else return null

        when {
            restServicesHolder.restService == null -> {
                log("!---- Authenticator skipped because UaaService is nulled in ServiceHolder ----!")
                return null
            }
            else -> {
                try {
                    Log.wtf("TRY / CATCH", "YEAH")
                    val tokenResponse: retrofit2.Response<Any> =
                        checkNotNull(restServicesHolder.restService)
                            .authenticate()
                            .execute()

                    if (tokenResponse.isSuccessful) {
                        tokenResponse.body()?.let {
                            //                            authorizationPreferenceSource.setToken(it.token)
                            log("!---- End Authenticator ----!")
                            return response.request().newBuilder()
                                .header(
                                    ConstantsApiKey.HTTP_HEADER_AUTHORIZATION,
                                    TODO("Replace with real token")
                                )
                                .build()

                        }
                    } else {
                        log("!---- Authenticator skipped because response is failed ----!")
                        return null
                    }
                } catch (e: Exception) {
                    log("!---- Authenticator skipped because request is failed ----!", e)
                    return null
                }
            }
        }

        log("!---- Authenticator skipped with unknown reason ----!")
        return null
    }

    private fun log(message: String, throwable: Throwable? = null) {
        if (BuildConfig.DEBUG) Platform.get().log(WARN, message, throwable)
    }
}