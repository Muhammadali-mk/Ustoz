package e.ustoz.data.model.category.category_lesson

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CategoryLessonResponse(
    @SerialName("courses")
    val courseList: List<Course>
)