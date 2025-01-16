package com.iug.coursehub.feature.user.home_screen.course_details_screen

import com.iug.coursehub.data.local.db.entity.Course

data class CourseDetails(
    val course: Course,
    val noOfEnrolledStudents: Int,
    val noOfLessons: Int
)
