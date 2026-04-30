package com.example.mapper;

import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface EmpMapper {
    /**
     * 条件查询员工信息
     * @return 查询到的emp列表
     */
    // sql语句在xml文件里
    List<Emp> list(EmpQueryParam empQueryParam);

    /**
     查询所有班主任（id+name）
     */
    List<Emp> listAll();

    /**
     * 新增员工信息
     */

    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            "values (#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id") // 告诉 MyBatis 把自增主键 赋值给 emp 对象的 id 字段
    void insert(Emp emp);

    /**
     * 删除员工
     */
    void delete(List<Integer> ids);

    /**
     * 编辑员工--数据回显
     */
    @Select("SELECT e.*, d.name as deptName " +
            "FROM emp e " +
            "LEFT JOIN dept d ON e.dept_id = d.id " +
            "WHERE e.id = #{id}")
    Emp findById(Integer id);

    /**
     * 编辑员工--编辑数据
     */
    void update(Emp emp);

    /**
     * 统计员工职位人数
     */
    @MapKey("pos")
    List<Map<String , Object>> countEmpJobData();

    /**
     * 统计员工性别人数
     */
    @MapKey("name")
    List<Map<String , Object>> countEmpGenderData();

    /**
     * 登录用：根据用户名和密码查询员工
     */
    Emp selectByUsernameAndPassword(Emp emp);
}
