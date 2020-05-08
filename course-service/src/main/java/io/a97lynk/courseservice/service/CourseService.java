package io.a97lynk.courseservice.service;

import io.a97lynk.courseservice.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {

    Page<Course> getListCourse(Pageable page);

    Course insertNewCourse(Course course);

    Course updateCourse(Course course);

    boolean deleteCourse(Integer courseId);
}
