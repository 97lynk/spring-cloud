package io.a97lynk.courseservice.service.impl;

import io.a97lynk.courseservice.repository.CourseRepository;
import io.a97lynk.courseservice.config.Producer;
import io.a97lynk.courseservice.entity.Course;
import io.a97lynk.courseservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final Producer producer;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, Producer producer) {
        this.courseRepository = courseRepository;
        this.producer = producer;
    }

    @Override
    public Page<Course> getListCourse(Pageable page) {
        return courseRepository.findAll(page);
    }

    @Override
    public Course insertNewCourse(Course course) {
        producer.send(course);
        course.setClosed(false);
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Course c) {
        Course course = courseRepository.findById(c.getId()).get();
        course.setName(c.getName());
        course.setNoOfMember(c.getNoOfMember());
        course.setPrice(c.getPrice());
        course.setStartDate(c.getStartDate());
        return courseRepository.save(course);
    }

    @Override
    public boolean deleteCourse(Integer courseId) {
        try {
            courseRepository.deleteById(courseId);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
