package io.a97lynk.studentservice.controller;

import io.a97lynk.studentservice.model.Student;
import io.a97lynk.studentservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAll() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable int id) {
        return studentService.findById(id);
    }

    @PostMapping
    public Student add(@RequestBody Student student) {
        studentService.add(student);
        return student;
    }
}
