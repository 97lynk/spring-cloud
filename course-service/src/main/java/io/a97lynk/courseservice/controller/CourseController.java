package io.a97lynk.courseservice.controller;

import io.a97lynk.courseservice.entity.Course;
import io.a97lynk.courseservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
@PreAuthorize("#oauth2.hasAnyScope('read')")
public class CourseController {

    private final CourseService courseService;


    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public Page<Course> getPageCourse(@PageableDefault Pageable pageable) {
        return courseService.getListCourse(pageable);
    }

    @PostMapping
    public Course postCourse(@RequestBody Course course) {
       return   courseService.insertNewCourse(course);

    }

    @PutMapping("/{courseId}")
    public Course putCourse(@RequestBody Course course, @PathVariable("courseId") Integer courseId) {
        course.setId(courseId);
        return courseService.updateCourse(course);
    }

    @DeleteMapping("/{courseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable("courseId") Integer courseId) {
        courseService.deleteCourse(courseId);
    }
}
