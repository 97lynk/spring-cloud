package io.a97lynk.studentservice;

import io.a97lynk.navigator.config.TenantContext;
import io.a97lynk.studentservice.mapper.StudentMapper;
import io.a97lynk.studentservice.model.Student;
import io.a97lynk.studentservice.service.StudentService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class StudentServiceTests {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    StudentService studentService;

    @BeforeAll
    public static void setUp() {
        TenantContext.setCurrentTenant("public");
    }

    @Test
    public void searchAllStudent() {
        List<Student> students = studentService.findAll();
        Optional<Student> optional = students.stream().filter(student -> student.getId() == 1).findFirst();

        Student studentExpect = new Student();
        studentExpect.setName("Nguyen Tuan");
        studentExpect.setHometown("Thu Duc District");
        studentExpect.setDateOfBirth(new Date(120, 7, 11));

        assertObject(studentExpect, optional.orElse(null));
    }

    @Test
    public void searchById() {
        Student student = studentService.findById(1);

        Student studentExpect = new Student();
        studentExpect.setName("Nguyen Tuan");
        studentExpect.setHometown("Thu Duc District");
        studentExpect.setDateOfBirth(new Date(120, 7, 11)); // 2020-08-11

        assertObject(studentExpect, student);
    }

    @Test
    public void createNewStudent() {
        Student student = new Student();
        student.setName("Tuan Tuan");
        student.setDateOfBirth(new Date());
        student.setHometown("Q2");

        studentService.add(student);

        assertObject(student, studentService.findById(student.getId()));
    }

    @Test
    public void updateById() {
        Student studentOriginal = new Student();
        studentOriginal.setName("Tuan Tuan to update");
        studentOriginal.setDateOfBirth(new Date());
        studentOriginal.setHometown("Q2 to update");
        studentService.add(studentOriginal);

        Student studentUpdate = new Student();
        studentUpdate.setId(studentOriginal.getId());
        studentUpdate.setName("Tuan Tuan Tuan");
        studentUpdate.setDateOfBirth(new Date(120, 9, 20)); // 2020-10-20
        studentUpdate.setHometown("Q8");
        studentService.update(studentUpdate);

        assertObject(studentUpdate, studentService.findById(studentOriginal.getId()));
    }

    @Test
    public void deleteById() {
        Student student = new Student();
        student.setName("Student to delete");
        student.setDateOfBirth(new Date());
        student.setHometown("Hoc Mon District");
        studentService.add(student);

        studentService.deleteById(student.getId());

        Assert.isNull(studentService.findById(student.getId()), "Deleted student is exist");
    }

    private void assertObject(Student studentExpect, Student studentActual) {
        System.out.println("==============================================================");
        System.out.println("| Expect data: " + studentExpect.toString());
        System.out.println("| Actual data: " + studentActual.toString());
        System.out.println("==============================================================");

        Assert.notNull(studentActual, "Student is null");
        Assert.isTrue(studentActual.getName().equals(studentExpect.getName()), "Wrong name");
        Assert.isTrue(studentActual.getHometown().equals(studentExpect.getHometown()), "Wrong hometown");
        Assert.isTrue(studentActual.getDateOfBirth().equals(studentExpect.getDateOfBirth()),
                "Wrong date of birth");
    }

}
