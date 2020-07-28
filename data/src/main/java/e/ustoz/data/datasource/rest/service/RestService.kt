package e.ustoz.data.datasource.rest.service

import e.ustoz.data.datasource.rest.constants.ConstantsApiPath
import retrofit2.Call
import retrofit2.http.POST

internal interface RestService {

    // Authenticate
    @POST(ConstantsApiPath.API_AUTH)
    fun authenticate(): Call<Any>
}