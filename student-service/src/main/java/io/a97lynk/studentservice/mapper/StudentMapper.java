package io.a97lynk.studentservice.mapper;

import io.a97lynk.studentservice.model.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper
public interface StudentMapper {

    @Select("SELECT * FROM ${tenantId}.Student")
    List<Student> findAll(String tenantId);

    @Insert("INSERT INTO ${tenantId}.Student(name, dateOfBirth, hometown) VALUES(#{student.name}, #{student.dateOfBirth}, #{student.hometown})")
    @Options(keyColumn = "id", keyProperty = "student.id", useGeneratedKeys = true, flushCache = Options.FlushCachePolicy.FALSE)
    Integer insert(@Param("student") Student student, @Param("tenantId") String tenantId);

    @Update("UPDATE ${tenantId}.Student SET name = #{student.name}, dateOfBirth = #{student.dateOfBirth}, hometown = #{student.hometown} WHERE id = #{student.id}")
    void update(Student student, String tenantId);

    @Delete("DELETE FROM ${tenantId}.Student WHERE id = #{studentId}")
    void deleteById(int studentId, String tenantId);

    @Select("SELECT * FROM ${tenantId}.Student WHERE id = #{studentId}")
    Student findById(int studentId, String tenantId);
}
