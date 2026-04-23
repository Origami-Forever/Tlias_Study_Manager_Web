package com.example.controller;

import com.example.pojo.*;
import com.example.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    /**
     * 班级分页查询
     */
    @GetMapping("/clazzs")
    public Result page(ClazzQueryParam clazzQueryParam)
    {
        log.info("班级分页查询: {}", clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 查询所有班级（下拉框用）
     * 路径：/clazzs/list   绝对不冲突！
     */
    @GetMapping("/clazzs/list")
    public Result list() {
        List<Clazz> list = clazzService.listAll();
        return Result.success(list);
    }

    /**
     * 删除班级
     */
    @DeleteMapping("/clazzs/{id}")
    public Result delete(@PathVariable Long id)
    {
        log.info("删除id为{}的班级", id);
        clazzService.delete(id);
        return Result.success();
    }

    /**
     * 添加班级
     */
    @PostMapping("/clazzs")
    public Result insert(@RequestBody Clazz clazz)
    {
        log.info("新增了一个班级");
        clazzService.add(clazz);
        return Result.success();
    }

    /**
     * 修改班级——回显
     */
    @GetMapping("/clazzs/{id}")
    public Result get(@PathVariable Long id)
    {
        log.info("查询id为{}的班级", id);
        Clazz clazz = clazzService.findById(id);
        return Result.success(clazz);
    }

    /**
     * 修改班级-提交
     */
    @PutMapping("/clazzs")
    public Result update(@RequestBody Clazz clazz) {
        log.info("修改班级：{}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }
}
