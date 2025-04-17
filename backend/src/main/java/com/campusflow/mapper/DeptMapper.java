package com.campusflow.mapper;

import org.apache.ibatis.annotations.*;
import com.campusflow.pojo.Dept;

import java.util.List;

@Mapper
public interface DeptMapper {
    @Select("select * from dept order by update_time desc;")
    List<Dept> findAll();

    @Delete("delete from dept where id=#{deptId}")
    void delete(Integer deptId);

    @Insert("Insert into dept(name,create_time,update_time) value(#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    @Select("select * from dept where id=#{id}")
    Dept findById(Integer id);

    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void update(Dept dept);
}
