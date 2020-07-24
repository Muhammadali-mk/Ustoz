package e.ustoz.data.model.teacher_information.online_lesson

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OnlineLesson(

    @SerialName("online")
    val type: String,

    @SerialName("lessonID")
    val lessonId: Long,

    @SerialName("userID")
    val userId: Long,

    @SerialName("hash")
    val hash: String,

    @SerialName("Code")
    val code: String
)