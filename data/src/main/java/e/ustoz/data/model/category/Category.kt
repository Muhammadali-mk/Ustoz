package e.ustoz.data.model.category

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Category(

    @SerialName("id")
    val categoryId: Long,

    @SerialName("name")
    val categoryName: String
)