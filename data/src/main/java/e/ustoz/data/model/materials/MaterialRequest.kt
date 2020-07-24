package e.ustoz.data.model.materials

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MaterialRequest(

    @SerialName("type")
    val material: String,

    @SerialName("courseID")
    val courseId: Long,

    @SerialName("user")
    val user: String,

    @SerialName("hash")
    val hash: String,

    @SerialName("code")
    val code: String
)