package e.ustoz.data.model.user_info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("type")
    val user: String,
    @SerialName("id")
    val userId: Long
)