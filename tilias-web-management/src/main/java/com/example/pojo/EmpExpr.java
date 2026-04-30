package com.example.pojo;

import lombok.Data;
import java.time.LocalDate;

/**
 * 工作经历
 */
@Data
public class EmpExpr {
    private Integer id;      // ID，主键
    private Integer empId;   // 关联的员工ID
    private LocalDate begin; // 工作开始时间
    private LocalDate end;   // 工作结束时间
    private String company;  // 公司名称
    private String job;      // 职位
}