package e.ustoz.data.model.authorization.phone_number

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhoneNumberRequest(
    @SerialName("type")
    val phone: String,

    @SerialName("hash")
    val hashCode: String,

    @SerialName("phone")
    val userPhone: String
)