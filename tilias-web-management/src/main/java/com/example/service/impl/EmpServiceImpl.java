package com.example.service.impl;

import com.example.mapper.EmpExprMapper;
import com.example.mapper.EmpMapper;
import com.example.pojo.*;
import com.example.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.example.utils.JwtUtils;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;

    /**
     *基于pageHelper实现分页查询
     */
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam){
        // 1.设置分页参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        // 2.执行查询
        List<Emp> empList = empMapper.list(empQueryParam);
        // 3.解析查询结果，并封装
        Page<Emp> p = (Page<Emp>)empList;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }

    /**
     *新增员工
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(Emp emp) {
        // 1.设置员工基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
        // 2.设置员工工作经历信息
        List<EmpExpr> exprList = emp.getExprList();
        if(exprList!=null && !exprList.isEmpty()){
            // 遍历集合，为empId赋值
            for (EmpExpr empExpr : exprList) {
                empExpr.setEmpId(emp.getId());
            }
            empExprMapper.insertBatch(exprList);
        }
    }

    /**
     * 删除员工
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteByIds(List<Integer> ids) {
        // 删除员工
        empMapper.delete(ids);
        // 删除员工工作经历
        empExprMapper.deleteBatch(ids);
    }

    /**
     * 编辑员工--回显数据
     */
    @Override
    public Emp findById(Integer id) {
        Emp emp = empMapper.findById(id);
        List<EmpExpr> exprList = empExprMapper.findByEmpId(id);
        emp.setExprList(exprList);
        return emp;
    }

    /**
     * 编辑员工--编辑数据
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        // 更新员工基本部分
        empMapper.update(emp);
        // 删除员工的工作经历
        empExprMapper.deleteBatch(Arrays.asList(emp.getId()));
        // 重新插入员工的工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if(exprList!=null && !exprList.isEmpty()){
            // 遍历集合，为empId赋值
            for (EmpExpr empExpr : exprList) {
                empExpr.setEmpId(emp.getId());
            }
            empExprMapper.insertBatch(exprList);
        }
    }

    /**
     * 查询所有员工
     */
    @Override
    public List<Emp> listAll() {
        return empMapper.listAll();
    }

    /**
     * 
     */
    @Override
    public LoginInfo login(Emp emp) {
        // 1. 调用mapper接口，根据用户名和密码查询员工信息
        Emp e = empMapper.selectByUsernameAndPassword(emp);

        // 2. 判断：是否存在这个员工，如果存在，组装登录成功信息
        if(e != null){
            log.info("登录成功，员工信息: {}", e);

            // 生成JWT令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("username", e.getUsername());
            String token = JwtUtils.generateToken(claims);
            return new LoginInfo(e.getId(), e.getUsername(), e.getName(), token);
        }
        // 3. 不存在，返回null
        return null;
    }

}
