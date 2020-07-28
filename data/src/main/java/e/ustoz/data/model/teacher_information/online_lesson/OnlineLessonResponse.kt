package e.ustoz.data.model.teacher_information.online_lesson

import kotlinx.serialization.Serializable

@Serializable
data class OnlineLessonResponse(
    val id: Long,
    val title: String,
    val imageUrl: String?=null
)