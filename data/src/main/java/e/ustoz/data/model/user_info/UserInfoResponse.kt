package e.ustoz.data.model.user_info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoResponse(
    @SerialName("name")
    val userName: String,

    @SerialName("birth")
    val userBirthDate: String,

    @SerialName("balance")
    val userBalance: String,

    @SerialName("currency")
    val currency: String,

    @SerialName("img")
    val userImgUrl: String
)