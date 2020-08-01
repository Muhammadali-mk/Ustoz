package e.ustoz.data.repository.course

import e.ustoz.data.model.teacher_information.online_lesson.OnlineLessonResponse
import e.ustoz.data.model.user_course.UserCourseResponse
import kotlinx.coroutines.flow.Flow

interface CourseRepository {

    fun getOnlineCources(): Flow<List<OnlineLessonResponse>>

    fun getUserCoursesByUserId(userId:Long):Flow<List<UserCourseResponse>>
}