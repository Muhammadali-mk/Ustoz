package e.ustoz.uz.presentation.features.main.features.course

import e.ustoz.data.utils.flow.collect
import e.ustoz.domain.interactor.main.course.CourseInteractor
import e.ustoz.uz.presentation.features.main.route.MainScreenRouteController
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.presenterScope
import javax.inject.Inject

class CoursesPresenter @Inject constructor(
    private val mainScreenRouteController: MainScreenRouteController,
    private val courseInteractor: CourseInteractor
) : MvpPresenter<CoursesView>() {
    override fun onFirstViewAttach() {
        getCourceData()
        getUserCourseById()
    }
    fun navigateToUserInfo() {
        mainScreenRouteController.navigateToUserInfoScreen()
    }

    private fun getCourceData() {
        presenterScope.launch {
            courseInteractor.getOnlineCources()
                .collect(
                    onStart = {viewState.onLoading()},
                    onSuccess = {viewState.onSuccess(it)},
                    onFailure = {viewState.onFailure(it)}
                )
        }
    }
    private fun getUserCourseById() {
        presenterScope.launch {
            courseInteractor.getUserCourses()
                .collect(
                    onStart = {viewState.onLoading()},
                    onSuccess = {viewState.onSuccessUserCources(it)},
                    onFailure = {viewState.onFailure(it)}
                )
        }
    }

}
