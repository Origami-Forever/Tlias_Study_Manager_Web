package com.example.controller;

import com.example.pojo.Dept;
import com.example.pojo.Result;
import com.example.service.DeptService;
import jakarta.servlet.ServletOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    public Result list(){
        System.out.println("查询全部部门");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    @RequestMapping(value = "/depts",method =  RequestMethod.DELETE)
    public Result delete(@RequestParam("id") Integer id){
        System.out.println("删除了id为"+ id +"的部门");
        deptService.deleteById(id);
        return Result.success();
    }

    @RequestMapping(value = "/depts",method = RequestMethod.POST)
    public Result add(@RequestBody Dept dept){
        System.out.println("新增了部门：" + dept);
        deptService.add(dept);
        return Result.success();
    }

    @RequestMapping(value = "/depts/{id}",method = RequestMethod.GET)
    public Result getInfo(@PathVariable Integer id){
        System.out.println("查询id为："+ id +"的部门信息");
        Dept dept = deptService.findById(id);
        return Result.success(dept);
    }

    @RequestMapping(value = "/depts",method = RequestMethod.PUT)
    public Result update(@RequestBody Dept dept){
        System.out.println("修改了id为"+dept.getName()+"的部门信息");
        deptService.update(dept);
        return Result.success();
    }
}
