package e.ustoz.domain.interactor.main.course

import e.ustoz.data.manager.coroutine.CoroutineContextManager
import e.ustoz.data.model.teacher_information.online_lesson.OnlineLessonResponse
import e.ustoz.data.repository.course.CourseRepository
import e.ustoz.data.utils.flow.flatMapResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CourseInteractor @Inject constructor(
    private val courseRepository: CourseRepository,
    private val coroutineContextManager: CoroutineContextManager
) {

    @Suppress("EXPERIMENTAL_API_USAGE")
    fun getOnlineCources(): Flow<Result<List<OnlineLessonResponse>>> {
        return courseRepository.getOnlineCources()
            .flatMapResult()
            .flowOn(coroutineContextManager.ioContext)
    }
}