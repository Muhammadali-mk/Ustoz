package e.ustoz.data.model.authorization.phone_number

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhoneNumberResponse(

    @SerialName("error")
    val error: Int,

    @SerialName("errorNote")
    val errorNote: String
)