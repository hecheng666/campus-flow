package com.campusflow.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.campusflow.pojo.Clazz;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ClazzsMapper {

    List<Clazz> findAll();

    @Delete("delete from clazz where id=#{id}")
    void delete(Integer id);

    long getCountByCondition(@Param("name") String name,
                             @Param("begin") LocalDate begin,
                             @Param("end") LocalDate end);

    List<Clazz> pageList(Integer start, Integer pageSize, String name, LocalDate begin, LocalDate end);

    void insert(Clazz clazz);

    Clazz findById(Integer id);

    void update(Clazz clazz);
}
