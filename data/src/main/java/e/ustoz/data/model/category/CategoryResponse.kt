package e.ustoz.data.model.category

import kotlinx.serialization.SerialName

data class CategoryResponse(

    @SerialName("categories")
    val categoryList: List<Category>
)