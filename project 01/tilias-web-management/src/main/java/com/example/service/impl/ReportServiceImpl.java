package com.example.service.impl;

import com.example.mapper.EmpMapper;
import com.example.pojo.JobOption;
import com.example.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;

    /**
     * 统计员工职位人数
     */
    @Override
    public JobOption getJobReport() {
        //1. 调用mapper接口，获取统计数据
        List<Map<String, Object>> list = empMapper.countEmpJobData();
        //2. 组装结果，并返回
        List<Object> jobList = list.stream()
                .map(dataMap -> dataMap.get("pos"))
                .toList();
        List<Object> dataList = list.stream()
                .map(dataMap -> dataMap.get("num"))
                .toList();
        return new JobOption(jobList, dataList);
    }

    /**
     * 统计员工性别人数
     */
    @Override
    public List<Map<String, Object>> getGenderReport() {
        return empMapper.countEmpGenderData();
    }
}
