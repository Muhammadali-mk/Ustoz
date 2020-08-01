package e.ustoz.data.repository.course

import e.ustoz.data.model.teacher_information.online_lesson.OnlineLessonResponse
import e.ustoz.data.model.user_course.UserCourseResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CourseRepositoryImpl : CourseRepository {

    override fun getOnlineCources(): Flow<List<OnlineLessonResponse>> {
        return flowOf(
            listOf(
                OnlineLessonResponse(2, "Базовый курс по структурам данных"),
                OnlineLessonResponse(3, "Проектирование механических "),
                OnlineLessonResponse(4, "Математика"),
                OnlineLessonResponse(5, "Математика"),
                OnlineLessonResponse(6, "FIVE"),
                OnlineLessonResponse(7, "физика"),
                OnlineLessonResponse(7, "философия"),
                OnlineLessonResponse(8, "Русский язык 1"),
                OnlineLessonResponse(9, "Русский язык 2")
            )
        )
    }

    override fun getUserCoursesByUserId(userId: Long): Flow<List<UserCourseResponse>> {
        return flowOf(
            listOf(
                UserCourseResponse(
                    courseId = 1,
                    courseTitle = "Обучение чтению за 15 уроков",
                    courseImgUrl = "",
                    courseVersion = "",
                    authorName = "Мадаминов Фарход Абдуллаевич",
                    lessonCount = 15,
                    courceProgress = 1,
                    dataServer = "",
                    testServer = ""
                ),
                UserCourseResponse(
                    courseId = 1,
                    courseTitle = "Учимся рисовать",
                    courseImgUrl = "",
                    courseVersion = "",
                    authorName = "Довлятов Ислом Эргашевич",
                    lessonCount = 20,
                    courceProgress = 5,
                    dataServer = "",
                    testServer = ""
                ),
                UserCourseResponse(
                    courseId = 1,
                    courseTitle = "Простая математика для студентов высш...",
                    courseImgUrl = "",
                    courseVersion = "",
                    authorName = "Мадаминов Фарход Абдуллаевич",
                    lessonCount = 17,
                    courceProgress = 0,
                    dataServer = "",
                    testServer = ""
                )
                , UserCourseResponse(
                    courseId = 1,
                    courseTitle = "Простая математика для студентов высш...",
                    courseImgUrl = "",
                    courseVersion = "",
                    authorName = "Мадаминов Фарход Абдуллаевич",
                    lessonCount = 17,
                    courceProgress = 0,
                    dataServer = "",
                    testServer = ""
                )

            )
        )

    }
}