package io.a97lynk.studentservice.mapper;

import io.a97lynk.studentservice.model.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper
public interface StudentMapper {

    @Select("SELECT * FROM Student")
    List<Student> findAll();

    @Insert("INSERT INTO Student(name, dateOfBirth, hometown) VALUES(#{name}, #{dateOfBirth}, ${hometown})")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true, flushCache = Options.FlushCachePolicy.FALSE)
    Integer insert(Student student);

    @Update("UPDATE Student SET name = #{name}, dateOfBirth = #{dateOfBirth}, hometown = #{hometown} WHERE id = #{id}")
    void update(Student student);

    @Delete("DELETE FROM Student WHERE id = #{studentId}")
    void deleteById(Integer studentId);

    @Select("SELECT * FROM Student WHERE id = #{studentId}")
    Student findById(int studentId);
}
