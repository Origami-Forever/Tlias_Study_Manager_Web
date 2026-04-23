package com.example.pojo;

import lombok.Data;

@Data
public class StudentQueryParam {
    private Integer page = 1;      // 页码
    private Integer pageSize = 10; // 每页展示的数量
    private String name;          // 姓名
    private Integer degree;        // 最高学历
    private Integer clazzId;      // 班级ID
}
