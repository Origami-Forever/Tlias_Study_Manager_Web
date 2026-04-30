package com.example.controller;

import com.example.pojo.*;
import com.example.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

// 自动生成日志
@Slf4j
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;

    /**
     * 分页查询
     */
    @GetMapping("/emps")
    public Result page(EmpQueryParam empQueryParam) {
        log.info("分页查询: {}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 查询所有员工（给班级下拉框用）
     */
    @GetMapping("/emps/list")  // 这里路径不一样，不会冲突！
    public Result list() {
        List<Emp> empList = empService.listAll();
        return Result.success(empList);
    }

    /**
     * 新增员工
     */
    @PostMapping("/emps")
    public Result add(@RequestBody Emp emp) {
        log.info("新增员工: {}", emp);
        empService.add(emp);
        return Result.success();
    }

    /**
     * 删除员工
     */
    @DeleteMapping("/emps")
    public Result delete(@RequestParam("ids") List<Integer> ids) {
        log.info("删除员工：{}",ids);
        empService.deleteByIds(ids);
        return Result.success();
    }

    /**
     * 编辑--回显员工
     */
    @GetMapping("/emps/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("查询id为：{}的员工", id);
        Emp emp= empService.findById(id);
        return Result.success(emp);
    }

    /**
     * 编辑--编辑员工
     */
    @PutMapping("/emps")
    public Result update(@RequestBody Emp emp) {
        log.info("修改了id为{}的员工", emp.getId());
        empService.update(emp);
        return  Result.success();
    }
}
