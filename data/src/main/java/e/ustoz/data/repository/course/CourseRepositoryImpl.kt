package e.ustoz.data.repository.course

import e.ustoz.data.model.teacher_information.online_lesson.OnlineLessonResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CourseRepositoryImpl : CourseRepository {

    override fun getOnlineCources(): Flow<List<OnlineLessonResponse>> {
        return flowOf(listOf(OnlineLessonResponse(2,"Базовый курс по структурам данных"),OnlineLessonResponse(3,"Проектирование механических "),OnlineLessonResponse(4,"Математика"),OnlineLessonResponse(5,"Математика"),OnlineLessonResponse(6,"FIVE"),OnlineLessonResponse(7,"физика"),OnlineLessonResponse(7,"философия"),OnlineLessonResponse(8,"Русский язык 1"),OnlineLessonResponse(9,"Русский язык 2")))
    }
}