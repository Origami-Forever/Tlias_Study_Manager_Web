package com.example.service.impl;

import com.example.mapper.ClazzMapper;
import com.example.pojo.Clazz;
import com.example.pojo.ClazzQueryParam;
import com.example.pojo.PageResult;
import com.example.service.ClazzService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;

    /**
     *班级分页查询
     */
    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        // 1.设置分页参数
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        // 2.执行查询
        List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);
        // 3.解析查询结果，并封装
        Page<Clazz> p = (Page<Clazz>)clazzList;
        // 4. 【新增】遍历集合，给每个班级计算并设置状态

        for (Clazz clazz : p.getResult()) {
            LocalDate begin = clazz.getBeginDate();
            LocalDate end = clazz.getEndDate();
            LocalDate current = LocalDate.now();

            if (current.isBefore(begin)) {
                clazz.setStatus("未开课");
            } else if (current.isAfter(end)) {
                clazz.setStatus("已结课");
            } else {
                clazz.setStatus("进行中");
            }
        }
        return new PageResult<Clazz>(p.getTotal(), p.getResult());
    }

    @Override
    public List<Clazz> listAll() {
        return clazzMapper.listAll();
    }

    /**
     * 删除指定id的班级
     */
    @Override
    public void delete(Long id) {
        clazzMapper.delete(id);
    }

    /**
     * 新增班级
     */
    @Override
    public void add(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }

    /**
     * 修改班级-回显
     *
     * @return
     */
    @Override
    public Clazz findById(Long id) {
        return clazzMapper.findById(id);
    }

    @Override
    public void update(Clazz clazz) {
        clazzMapper.update(clazz);
    }
}
