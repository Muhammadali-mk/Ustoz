package e.ustoz.data.model.authorization.sms_code

import kotlinx.serialization.SerialName

data class SmsCodeRequest(
    @SerialName("type")
    val code: String,

    @SerialName("hash")
    val hashCode: String,

    @SerialName("code")
    val smsCode: String
)