package com.example.controller;

import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.pojo.Student;
import com.example.pojo.StudentQueryParam;
import com.example.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 分页查询
     */
    @GetMapping("/students")
    public Result page(StudentQueryParam studentQueryParam) {
        log.info("分页查询: {}", studentQueryParam);
        PageResult<Student> pageResult = studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 查询所有学生（给下拉框用）
     */
    @GetMapping("/students/list")
    public Result list() {
        List<Student> studentList = studentService.listAll();
        return Result.success(studentList);
    }

    /**
     * 新增学生
     */
    @PostMapping("/students")
    public Result add(@RequestBody Student student) {
        log.info("新增学生: {}", student);
        studentService.add(student);
        return Result.success();
    }

    /**
     * 删除学生（路径变量版）
     * 访问格式：DELETE /students/1,2,3
     */
    @DeleteMapping("/students/{ids}")
    public Result delete(@PathVariable("ids") List<Integer> ids) {
        log.info("删除学生：{}", ids);
        studentService.deleteByIds(ids);
        return Result.success();
    }

    /**
     * 编辑--回显学生
     */
    @GetMapping("/students/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("查询id为：{}的学生", id);
        Student student = studentService.findById(id);
        return Result.success(student);
    }

    /**
     * 编辑--修改学生
     */
    @PutMapping("/students")
    public Result update(@RequestBody Student student) {
        log.info("修改了id为{}的学生", student.getId());
        studentService.update(student);
        return Result.success();
    }

    /**
     * 违纪处理
     */
    @PutMapping("/students/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("id为{}的学生扣分{}分", id, score);
        studentService.violation(id, score);
        return Result.success();
    }
}
