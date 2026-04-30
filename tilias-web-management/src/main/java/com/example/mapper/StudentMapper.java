package com.example.mapper;

import com.example.pojo.Student;
import com.example.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {
    /**
     * 查询学生
     */
    List<Student> list(StudentQueryParam studentQueryParam);

    List<Student> listAll();

    /**
     * 删除指定id的学生
     */
    void delete(List<Integer> ids);

    /**
     * 添加学生
     * 对应数据库表：student
     */
    @Insert("insert into student(name, no, gender, phone, id_card, is_college, address, degree, graduation_date, clazz_id, create_time, update_time) " +
            "values(#{name}, #{no}, #{gender}, #{phone}, #{idCard}, #{isCollege}, #{address}, #{degree}, #{graduationDate}, #{clazzId}, #{createTime}, #{updateTime})")
    void insert(Student student);

    /**
     * 修改学生-回显
     */
    @Select("select s.*" +
            "from student s " +
            "where s.id = #{id}")
    Student findById(Integer id);

    /**
     * 修改学生
     */
    @Update("update student set " +
            "name = #{name}, " +
            "no = #{no}, " +
            "gender = #{gender}, " +
            "phone = #{phone}, " +
            "id_card = #{idCard}, " +
            "is_college = #{isCollege}, " +
            "address = #{address}, " +
            "degree = #{degree}, " +
            "graduation_date = #{graduationDate}, " +
            "clazz_id = #{clazzId}, " +
            "update_time = now() " +
            "where id = #{id}")
    void update(Student student);

    /**
     * 处理违纪
     */
    @Update("update student set " +
            "violation_count = violation_count+1 , " +
            "violation_score = violation_score+ #{score} " +
            "where id = #{id}")
    void violation(Integer id, Integer score);
}
