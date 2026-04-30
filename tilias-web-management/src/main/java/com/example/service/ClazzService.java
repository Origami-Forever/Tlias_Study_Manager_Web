package com.example.service;

import com.example.pojo.Clazz;
import com.example.pojo.ClazzQueryParam;
import com.example.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);
    List<Clazz> listAll(); // 加这一行

    void delete(Long id);

    void add(Clazz clazz);

    Clazz findById(Long id);

    void update(Clazz clazz);
}
