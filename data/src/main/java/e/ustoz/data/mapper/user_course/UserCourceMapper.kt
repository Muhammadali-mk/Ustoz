package e.ustoz.data.mapper.user_course

import e.ustoz.data.model.user_course.UserCourse
import e.ustoz.data.model.user_course.UserCourseResponse

/*
internal fun UserCourcesResponse.map() =
    List<UserCourseResponse>
*/

internal fun List<UserCourseResponse>.map() =
    map { it.mapToCourse() }

internal fun UserCourseResponse.mapToCourse() =
    UserCourse(
        courseId = courseId,
        courseTitle = courseTitle,
        courseImgUrl = courseImgUrl,
        courseVersion = courseVersion,
        authorName = authorName
    )