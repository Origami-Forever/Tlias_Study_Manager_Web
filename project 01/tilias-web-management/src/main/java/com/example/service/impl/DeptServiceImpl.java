package com.example.service.impl;

import com.example.mapper.DeptMapper;
import com.example.pojo.Dept;
import com.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

// 可被controller调用
@Service
public class DeptServiceImpl implements DeptService {

    // 使用自动注入mapper层的DeptMapper
    @Autowired
    private DeptMapper deptMapper;

    // 查询所有部门
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    // 删除指定id的部门
    @Override
    public void deleteById(Integer id) {
        deptMapper.deleteById(id);
    }

    // 新增指定name的部门
    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    // 获取指定id的部门信息
    @Override
    public Dept findById(Integer id) {
        return deptMapper.findById(id);
    }

    //更新指定id的部门数据
    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
