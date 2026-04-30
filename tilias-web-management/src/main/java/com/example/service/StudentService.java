package com.example.service;

import com.example.pojo.PageResult;
import com.example.pojo.Student;
import com.example.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    PageResult<Student> page(StudentQueryParam studentQueryParam);

    void add(Student student);

    void deleteByIds(List<Integer> ids);

    Student findById(Integer id);

    void update(Student student);

    List<Student> listAll();

    void violation(Integer id, Integer score);
}