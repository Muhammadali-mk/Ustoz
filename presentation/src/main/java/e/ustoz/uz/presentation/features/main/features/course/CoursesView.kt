package e.ustoz.uz.presentation.features.main.features.course

import e.ustoz.data.model.teacher_information.online_lesson.OnlineLessonResponse
import moxy.MvpView

interface CoursesView : MvpView {

    fun onLoading()

    fun onSuccess(list: List<OnlineLessonResponse>)

    fun onFailure(throwable: Throwable)
}