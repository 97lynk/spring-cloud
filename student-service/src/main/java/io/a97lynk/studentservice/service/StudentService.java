package io.a97lynk.studentservice.service;

import io.a97lynk.navigator.config.TenantContext;
import io.a97lynk.studentservice.mapper.StudentMapper;
import io.a97lynk.studentservice.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentMapper studentMapper;

    @Autowired
    public StudentService(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    public List<Student> findAll() {
        return studentMapper.findAll(TenantContext.getCurrentTenant());
    }

    public Student findById(int studentId) {
        return studentMapper.findById(studentId, TenantContext.getCurrentTenant());
    }

    public Student add(Student student) {
        studentMapper.insert(student, TenantContext.getCurrentTenant());
        return student;
    }

    public Student update(Student student) {
        studentMapper.update(student, TenantContext.getCurrentTenant());
        return student;
    }

    public void deleteById(int studentId) {
        studentMapper.deleteById(studentId, TenantContext.getCurrentTenant());
    }

}
