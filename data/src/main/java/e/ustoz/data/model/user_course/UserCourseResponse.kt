package e.ustoz.data.model.user_course

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserCourseResponse(

    @SerialName("courceID")
    val courseId: Long,

    @SerialName("title")
    val courseTitle: String,

    @SerialName("img")
    val courseImgUrl: String,

    @SerialName("version")
    val courseVersion: String,

    @SerialName("authorName")
    val authorName: String,

    @SerialName("lessonCount")
    val lessonCount: Int,

    @SerialName("courceProgress")
    val courceProgress: Int,

    @SerialName("dataServer")
    val dataServer: String,
    @SerialName("testServer")
    val testServer: String
)