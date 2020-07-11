package e.ustoz.data.datasource.rest.error

import e.ustoz.data.datasource.rest.retrofit.interceptor.HttpErrorResponse
import e.ustoz.data.datasource.rest.retrofit.interceptor.HttpErrorResponseMapper

internal class ErrorResponseMapper : HttpErrorResponseMapper {

    override fun onHandleResponse(httpErrorCode: Int, response: String?): HttpErrorResponse? =
        null
}