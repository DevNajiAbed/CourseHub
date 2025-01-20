package com.iug.coursehub.feature.user.home_screen.course_details_screen;

import com.iug.coursehub.data.local.db.entity.Course;

public class CourseDetails {

    private final Course course;
    private final int noOfEnrolledStudents;
    private final int noOfLessons;

    public CourseDetails(Course course, int noOfEnrolledStudents, int noOfLessons) {
        this.course = course;
        this.noOfEnrolledStudents = noOfEnrolledStudents;
        this.noOfLessons = noOfLessons;
    }

    public Course getCourse() {
        return course;
    }

    public int getNoOfEnrolledStudents() {
        return noOfEnrolledStudents;
    }

    public int getNoOfLessons() {
        return noOfLessons;
    }
}
