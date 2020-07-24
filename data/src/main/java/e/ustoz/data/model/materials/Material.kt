package e.ustoz.data.model.materials

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Material(

    @SerialName("id")
    val materialId: Long,
    @SerialName("type")
    val materialType: String,
    @SerialName("fileName")
    val fileName: String,

    @SerialName("server")
    val materialUrl: String,

    @SerialName("courseName")
    val courseName: String
)