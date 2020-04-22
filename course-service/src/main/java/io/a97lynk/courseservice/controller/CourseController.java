package io.a97lynk.courseservice.controller;

import io.a97lynk.courseservice.Page;
import io.a97lynk.courseservice.entity.Course;
import io.a97lynk.courseservice.service.CourseService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
@PreAuthorize("#oauth2.hasAnyScope('read')")
public class CourseController {

    private final CourseService courseService;

    private final SqlSession sqlSession;

    @Autowired
    public CourseController(CourseService courseService, SqlSession sqlSession) {
        this.courseService = courseService;
        this.sqlSession = sqlSession;
    }

    @GetMapping
    public Page<Course> getPageCourse(@RequestParam(required = false, defaultValue = "0") int page,
                                      @RequestParam(required = false, defaultValue = "10") int size) {
        return courseService.getListCourse(Page.builder().page(page).size(size).build());
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
