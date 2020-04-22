package io.a97lynk.courseservice.service;

import io.a97lynk.courseservice.Page;
import io.a97lynk.courseservice.entity.Course;

public interface CourseService {

    Page<Course> getListCourse(Page page);

    Course insertNewCourse(Course course);

    Course updateCourse(Course course);

    boolean deleteCourse(Integer courseId);
}
