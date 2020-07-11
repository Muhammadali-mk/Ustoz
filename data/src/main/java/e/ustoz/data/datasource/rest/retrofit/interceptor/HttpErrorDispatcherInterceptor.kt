package e.ustoz.data.datasource.rest.retrofit.interceptor

import okhttp3.Interceptor
import okhttp3.Response

internal class HttpErrorDispatcherInterceptor private constructor(
    private val handlers: Array<HttpErrorResponseHandler>,
    private val mappers: Array<HttpErrorResponseMapper>
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }

    private fun getErrorResponse(httpErrorCode: Int): HttpErrorResponse =
        object : HttpErrorResponse {
            override val httpErrorCode: Int = httpErrorCode
        }

    companion object {
        private const val TAG: String = "Error Interceptor"

        class Builder {
            private val handlers: MutableList<HttpErrorResponseHandler> = arrayListOf()
            private val mappers: MutableList<HttpErrorResponseMapper> = arrayListOf()

            fun setResponseHandlers(vararg handlers: HttpErrorResponseHandler): Builder {
                this.handlers.apply { clear(); addAll(handlers) }
                return this
            }

            fun setResponseMappers(vararg mappers: HttpErrorResponseMapper): Builder {
                this.mappers.apply { clear(); addAll(mappers) }
                return this
            }

            fun build(): HttpErrorDispatcherInterceptor =
                HttpErrorDispatcherInterceptor(
                    handlers = handlers.toTypedArray(),
                    mappers = mappers.toTypedArray()
                )
        }
    }
}