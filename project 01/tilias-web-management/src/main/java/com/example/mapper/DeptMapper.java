package com.example.mapper;

import com.example.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

// Mapper注解可以让service层使用该层
@Mapper
public interface DeptMapper {

    /**
    查询所有的部门数据
    */
    @Select("SELECT id, `NAME`, create_time, update_time FROM dept ORDER BY update_time DESC;")
    List<Dept> findAll();

    /**
     * 删除指定id的部门数据
     */
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    /**
     * 新增指定name的部门数据
     */
    @Insert("insert into dept(NAME, create_time, update_time) values(#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);

    /**
     * 查询回显指定id的部门数据
     */
    @Select("SELECT id, `NAME`, create_time, update_time  from dept where id = #{id}")
    Dept findById(Integer id);

    /**
     * 更新指定的部门数据
     */
    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);
}
