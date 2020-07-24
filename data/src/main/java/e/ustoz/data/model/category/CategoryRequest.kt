package e.ustoz.data.model.category

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryRequest(
    @SerialName("type")
    val categories: String
)