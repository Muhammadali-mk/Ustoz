package e.ustoz.data.provider.repository

import e.ustoz.data.repository.course.CourseRepository
import e.ustoz.data.repository.state.StateRepository

interface DataRepositoryProvider {

    //val authenticateRepository: AuthenticateRepository

    val stateRepository:StateRepository

    val courseRepository:CourseRepository

}