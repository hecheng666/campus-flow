package com.campusflow.mapper;

import org.apache.ibatis.annotations.*;
import com.campusflow.pojo.Emp;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

    /**
     * 按查询条件统计总数
     */
    long getCountByCondition(@Param("name") String name,
                             @Param("gender") Integer gender,
                             @Param("begin") LocalDate begin,
                             @Param("end") LocalDate end);

    /**
     * 分页条件查询
     */
    List<Emp> pageList(Integer start, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    void deleteByIds(List<Integer> ids);

    Emp selectById(Integer id);

    void update(Emp emp);

    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    @MapKey("name")
    List<Map<String, Object>> countGenderData();

    List<Emp> findAll();

    @Select("select * from emp where username = #{username}")
    Emp selectByUsername(String username);

    @Update("update emp set password = #{password}, update_time = now() where id = #{id}")
    void updatePassword(@Param("id") Integer id, @Param("password") String password);
}
