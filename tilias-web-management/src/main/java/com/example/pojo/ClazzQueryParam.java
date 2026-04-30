package com.example.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ClazzQueryParam {
    private Integer page = 1; // 页码
    private Integer pageSize = 10; // 每页展示的数量
    private String name; // 班级名
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin; // 开课时间（起始）
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end; // 结课时间（最终）
}
