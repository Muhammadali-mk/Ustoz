package e.ustoz.data.model.category.category_lesson

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CategoryLessonRequest(

    @SerialName("type")
    val courses: String,

    @SerialName("categoryId")
    val categoryId: Long
)