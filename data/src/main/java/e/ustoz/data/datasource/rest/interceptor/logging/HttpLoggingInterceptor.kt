package e.ustoz.data.datasource.rest.interceptor.logging

import e.ustoz.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

internal class HttpLoggingInterceptor : Interceptor {
    private val interceptor = HttpLoggingInterceptor().setLevel(
        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
    )

    override fun intercept(chain: Interceptor.Chain): Response =
        interceptor.intercept(chain)
}