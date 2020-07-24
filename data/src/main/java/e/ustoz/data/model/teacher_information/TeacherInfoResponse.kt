package e.ustoz.data.model.teacher_information

import kotlinx.serialization.SerialName

data class TeacherInfoResponse(

    @SerialName("error")
    val error: Int,

    @SerialName("errorNote")
    val errorNote: String,

    @SerialName("name")
    val teacherName: String,

    @SerialName("level")
    val teacherLevel: String,

    @SerialName("Img")
    val teacherImage: String,

    @SerialName("Info")
    val teacherInfo: String,

    @SerialName("Courses")
    val list: List<TeacherCourseResponse>


)