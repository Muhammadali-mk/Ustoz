package e.ustoz.data.model.authorization.sms_code

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SmsCodeResponse(

    @SerialName("error")
    val error: Int,

    @SerialName("errorNote")
    val errorNote: String,

    @SerialName("id")
    val userId: Long
)