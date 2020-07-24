package e.ustoz.data.model.teacher_information.teacher_lesson

import java.io.File

data class LessonRequest internal constructor(

    val lessonFile: File?,

    val lessonId: Long,

    val userId: Long,

    val hashCode: String,

    val userCode: String
)
