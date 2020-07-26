package e.ustoz.data.datasource.rest.interceptor.token

import e.ustoz.data.datasource.preference.authorization.AuthorizationPreferenceSource
import e.ustoz.data.datasource.rest.constants.ConstantsApiKey
import e.ustoz.data.datasource.rest.constants.ConstantsApiPath
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Request
import okhttp3.Response

internal class HeaderAccessTokenInterceptor(
    private val authorizationPreferenceSource: AuthorizationPreferenceSource
) : Interceptor {

    override fun intercept(chain: Chain): Response =
        chain.proceed(addHeaderToRequest(chain.request()))

    private fun addHeaderToRequest(oldRequest: Request): Request = oldRequest.let { it ->
        return@let it.newBuilder()
            .apply {
                val httpUrl: HttpUrl = it.url()
                if (!it.url().encodedPath().contains(
                        ConstantsApiPath.API_AUTHENTICATE,
                        ignoreCase = true
                    )
                )
                    authorizationPreferenceSource.accessToken?.let {
                        addHeader(ConstantsApiKey.HTTP_HEADER_AUTHORIZATION, it)
                    }
            }
            .url(it.url())
            .build()
    }
}