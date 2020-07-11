package e.ustoz.data.datasource.rest.retrofit.exception

import e.ustoz.data.datasource.rest.retrofit.interceptor.HttpErrorResponse
import java.io.IOException

open class RestErrorException(open val response: HttpErrorResponse) : IOException()