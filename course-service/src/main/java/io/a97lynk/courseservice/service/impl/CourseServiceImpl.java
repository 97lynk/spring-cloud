package io.a97lynk.courseservice.service.impl;

import io.a97lynk.courseservice.Page;
import io.a97lynk.courseservice.entity.Course;
import io.a97lynk.courseservice.mapper.CourseMapper;
import io.a97lynk.courseservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseMapper courseMapper;

    @Autowired
    public CourseServiceImpl(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    @Override
    public Page<Course> getListCourse(Page page) {
        List<Course> courses = courseMapper.findAll(page);
        page.setContent(courses);
        return page;
    }

    @Override
    public Course insertNewCourse(Course course) {
        courseMapper.insertCourse(course);
        return course;
    }

    @Override
    public Course updateCourse(Course course) {
        courseMapper.update(course);
        return courseMapper.findById(course.getId());
    }

    @Override
    public boolean deleteCourse(Integer courseId) {
        try {
            courseMapper.deleteById(courseId);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
