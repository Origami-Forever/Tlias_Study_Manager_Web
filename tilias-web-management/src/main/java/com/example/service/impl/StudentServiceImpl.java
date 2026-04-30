package com.example.service.impl;

import com.example.mapper.StudentMapper;
import com.example.pojo.PageResult;
import com.example.pojo.Student;
import com.example.pojo.StudentQueryParam;
import com.example.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 基于pageHelper实现分页查询
     */
    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam){
        // 1.设置分页参数
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
        // 2.执行查询
        List<Student> studentList = studentMapper.list(studentQueryParam);
        // 3.解析查询结果，并封装
        Page<Student> p = (Page<Student>)studentList;
        return new PageResult<Student>(p.getTotal(), p.getResult());
    }

    /**
     * 查询所有学生
     */
    @Override
    public List<Student> listAll() {
        return studentMapper.listAll();
    }

    /**
     * 处理违纪
     */
    @Override
    public void violation(Integer id, Integer score) {
        studentMapper.violation(id, score);
    }

    /**
     *新增员工
     */
    @Override
    public void add(Student student){
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.insert(student);
    }

    /**
     * 删除学生
     */
    @Override
    public void deleteByIds(List<Integer> ids) {
        // 删除学生
        studentMapper.delete(ids);
    }

    /**
     * 编辑学生--回显数据
     */
    @Override
    public Student findById(Integer id) {
        Student student = studentMapper.findById(id);
        return student;
    }

    /**
     * 编辑学生--编辑数据
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Student student) {
        studentMapper.update(student);
    }
}
