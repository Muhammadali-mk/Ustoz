package e.ustoz.data.model.teacher_information

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeacherCourseResponse(
    @SerialName("courseId")
    val courseId: Long,

    @SerialName("title")
    val courseTitle: String,

    @SerialName("img")
    val courseImage: String
)