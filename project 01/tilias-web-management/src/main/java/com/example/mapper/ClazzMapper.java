package com.example.mapper;

import com.example.pojo.Clazz;
import com.example.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClazzMapper {

    /**
     * 查询班级
     */
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    List<Clazz> listAll();

    /**
     * 删除指定id的班级
     */
    @Delete("delete from clazz where id = #{id}")
    void delete(Long id);

    /**
     * 添加班级
     */
    @Insert("insert into clazz(name, room, begin_date, end_date, master_id, subject, create_time, update_time) " +
            "values(#{name}, #{room}, #{beginDate}, #{endDate}, #{masterId}, #{subject}, #{createTime}, #{updateTime})")
    void insert(Clazz clazz);

    /**
     * 修改班级-回显
     */
    @Select("select c.*, e.name masterName " +
            "from clazz c left join emp e on c.master_id = e.id " +
            "where c.id = #{id}")
    Clazz findById(Long id);

    /**
     * 修改班级
     */
    @Update("update clazz set " +
            "name = #{name}, " +
            "room = #{room}, " +
            "master_id = #{masterId}, " +
            "update_time = now() " +
            "where id = #{id}")
    void update(Clazz clazz);
}
