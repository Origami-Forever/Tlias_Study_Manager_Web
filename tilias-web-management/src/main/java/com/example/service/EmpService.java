package com.example.service;

import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
import com.example.pojo.LoginInfo;
import com.example.pojo.PageResult;

import java.util.List;

public interface EmpService {
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void add(Emp emp);

    void deleteByIds(List<Integer> ids);

    Emp findById(Integer id);

    void update(Emp emp);

    List<Emp> listAll(); // 加这一行

    LoginInfo login(Emp emp);


    // 实现类

}
