package e.ustoz.data.model.materials

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MaterialResponse(
    @SerialName("error")
    val error: Int,

    @SerialName("errorNote")
    val errorNote: String
)