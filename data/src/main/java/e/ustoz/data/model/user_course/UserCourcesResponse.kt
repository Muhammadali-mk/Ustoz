package e.ustoz.data.model.user_course

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserCourcesResponse(

    @SerialName("error")
    val error: Int,

    @SerialName("errorNote")
    val errrorNote: String,

    @SerialName("cources")
    val coursesList: List<UserCourseResponse>
)