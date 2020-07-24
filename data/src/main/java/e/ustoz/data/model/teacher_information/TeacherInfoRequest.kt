package e.ustoz.data.model.teacher_information

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeacherInfoRequest(
    @SerialName("type")
    val teacher: String,

    @SerialName("userId")
    val userId: Long,

    @SerialName("hash")
    val hash: String,

    @SerialName("userCode")
    val code: String
)