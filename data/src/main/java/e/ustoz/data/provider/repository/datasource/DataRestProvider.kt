package e.ustoz.data.provider.repository.datasource

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import e.ustoz.data.BuildConfig
import e.ustoz.data.datasource.rest.authenticator.Authenticator
import e.ustoz.data.datasource.rest.constants.ConstantsApiPath
import e.ustoz.data.datasource.rest.error.ErrorResponseHandler
import e.ustoz.data.datasource.rest.error.ErrorResponseMapper
import e.ustoz.data.datasource.rest.interceptor.token.HeaderAccessTokenInterceptor
import e.ustoz.data.datasource.rest.retrofit.adapter.FlowCallAdapterFactory
import e.ustoz.data.datasource.rest.retrofit.converter.UnitConverterFactory
import e.ustoz.data.datasource.rest.retrofit.interceptor.withHttpErrorDispatcher
import e.ustoz.data.datasource.rest.service.RestService
import e.ustoz.data.datasource.rest.service.holder.RestServicesHolder
import kotlinx.serialization.json.Json
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import uz.anotomica.app.data.datasource.preference.authorization.AuthorizationPreferenceSource
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.*
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

internal class DataRestProvider(authorizationPreferenceSource: AuthorizationPreferenceSource) {
    private val restServicesHolder: RestServicesHolder = RestServicesHolder()


    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
    }

    private val okHttpClientBuilder: OkHttpClient.Builder =
        OkHttpClient.Builder()
            .authenticator(Authenticator(restServicesHolder, authorizationPreferenceSource))
            .addInterceptor(loggingInterceptor)
            .addInterceptor(HeaderAccessTokenInterceptor(authorizationPreferenceSource))
            .withHttpErrorDispatcher(ErrorResponseHandler(), ErrorResponseMapper())
            .retryOnConnectionFailure(false)
            .followRedirects(false)
            .followSslRedirects(false)
            .connectTimeout(CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)

    private val retrofit: Retrofit by lazy {
        @Suppress("EXPERIMENTAL_API_USAGE")
        return@lazy Retrofit.Builder()
            .baseUrl(ConstantsApiPath.API_BASE_URL)
            .client(okHttpClientBuilder.build())
//            .client(getUnsafeOkHttpClient(authorizationPreferenceSource).build())
            .addCallAdapterFactory(FlowCallAdapterFactory)
            .addConverterFactory(Json.nonstrict.asConverterFactory(MediaType.get("application/json; charset=utf-8")))
            .addConverterFactory(UnitConverterFactory)
            .build()
    }

    val restService: RestService =
        retrofit.create(RestService::class.java)
            .also { restServicesHolder.restService = it }

    private fun getUnsafeOkHttpClient(authorizationPreferenceSource: AuthorizationPreferenceSource): OkHttpClient.Builder {
        val trustAllCerts: Array<TrustManager> = arrayOf<TrustManager>(
            object : X509TrustManager {
                override fun checkClientTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?
                ) {
                }

                override fun checkServerTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?
                ) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf<java.security.cert.X509Certificate>()
                }
            }
        )

        val spec = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
            .tlsVersions(TlsVersion.TLS_1_1, TlsVersion.TLS_1_2, TlsVersion.TLS_1_0)
            .cipherSuites(
                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256,
                CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,
                CipherSuite.TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA
            )
            .build()

        var sslContext: SSLContext = SSLContext.getInstance("SSL").apply {
            init(null, trustAllCerts, SecureRandom())
        }
        val sslSocketFactory: SSLSocketFactory = sslContext.getSocketFactory()


        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
        }

        val okHttpClientBuilder: OkHttpClient.Builder =
            OkHttpClient.Builder()
                .authenticator(
                    Authenticator(
                        restServicesHolder,
                        authorizationPreferenceSource
                    )
                )
                .addInterceptor(loggingInterceptor)
                .addInterceptor(HeaderAccessTokenInterceptor(authorizationPreferenceSource))
                .withHttpErrorDispatcher(ErrorResponseHandler(), ErrorResponseMapper())
                .retryOnConnectionFailure(false)
                .followRedirects(false)
                .followSslRedirects(false)
                .connectTimeout(CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)

                .sslSocketFactory(sslSocketFactory)
                .connectionSpecs(Collections.singletonList(spec))
                .hostnameVerifier { hostname, session -> true }

        return okHttpClientBuilder
    }

    private companion object {
        const val CONNECTION_TIMEOUT_SECONDS: Long = 160
    }
}