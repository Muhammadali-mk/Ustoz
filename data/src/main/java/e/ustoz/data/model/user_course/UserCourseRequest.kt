package e.ustoz.data.model.user_course

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserCourseRequest(

    @SerialName("Type")
    val type: String,

    @SerialName("userID ")
    val userId: Long,

    @SerialName("Hash")
    val hashCode: String,

    @SerialName("Code")
    val smsCode: String
)