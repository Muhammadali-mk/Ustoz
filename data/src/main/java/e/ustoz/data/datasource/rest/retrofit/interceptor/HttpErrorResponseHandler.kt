package e.ustoz.data.datasource.rest.retrofit.interceptor

import java.io.IOException

internal interface HttpErrorResponseHandler : HttpErrorDispatcherComponent {

    fun onHandleError(
        httpErrorCode: Int,
        errorResponse: HttpErrorResponse
    ): IOException?
}