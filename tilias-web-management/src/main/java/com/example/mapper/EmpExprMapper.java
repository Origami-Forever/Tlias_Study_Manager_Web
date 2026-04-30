package com.example.mapper;

import com.example.pojo.EmpExpr;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface EmpExprMapper {

    /**
     * 批量保存员工的工作经历信息
     */
    void insertBatch(List<EmpExpr> exprList);

    /**
     * 删除员工的工作经历信息
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 编辑员工，数据回显
     */
    @Select("select * from emp_expr e where e.emp_id = #{emp_id}")
    List<EmpExpr> findByEmpId(Integer emp_id);
}
