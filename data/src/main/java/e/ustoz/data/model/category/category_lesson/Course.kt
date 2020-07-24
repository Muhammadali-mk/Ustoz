package e.ustoz.data.model.category.category_lesson

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class Course(
    @SerialName("courseId")
    val courseId:Long,

    @SerialName("title")
    val courseTitle:String,

    @SerialName("img")
    val courseImgID:String,

    @SerialName("version")
    val courseVersion:String,

    @SerialName("authorName")
    val authorName:String,

    @SerialName("server")
    val courseUrl:String
)