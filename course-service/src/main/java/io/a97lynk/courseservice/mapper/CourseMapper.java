package io.a97lynk.courseservice.mapper;

import io.a97lynk.courseservice.Page;
import io.a97lynk.courseservice.entity.Course;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface CourseMapper {

    @Select("SELECT * FROM Course LIMIT  #{page.size} OFFSET #{page.offset}")
    List<Course> findAll(@Param("page") Page page);

    @Insert("INSERT INTO Course (name, startDate, price, noOfMember) VALUES(#{name}, #{startDate}, #{price}, #{noOfMember})")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true, flushCache = Options.FlushCachePolicy.FALSE)
    Integer insertCourse(Course course);

    @Update("UPDATE Course SET name = #{name}, startDate = #{startDate}, price = #{price}, noOfMember = #{noOfMember} WHERE id = #{id}")
    void update(Course course);

    @Delete("DELETE FROM Course WHERE id = #{courseId}")
    void deleteById(Integer courseId);

    @Select("SELECT * FROM Course WHERE id = #{courseId}")
    Course findById(int courseId);

}
