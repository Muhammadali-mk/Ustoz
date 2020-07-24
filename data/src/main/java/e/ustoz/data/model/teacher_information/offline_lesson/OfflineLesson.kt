package e.ustoz.data.model.teacher_information.offline_lesson

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OfflineLesson(

    @SerialName("offline")
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