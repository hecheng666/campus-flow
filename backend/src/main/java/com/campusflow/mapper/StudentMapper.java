package com.campusflow.mapper;

import org.apache.ibatis.annotations.*;
import com.campusflow.pojo.Student;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    @Select("select s.*,c.name clazzName from student s left join clazz c on s.clazz_id = c.id where s.id=#{id};")
    Student findById(Integer id);

    long getCountByCondition(@Param("name") String name,
                             @Param("degree") Integer degree,
                             @Param("clazzId") Integer clazzId);

    List<Student> pageList(Integer start, Integer pageSize, String name, Integer degree, Integer clazzId);

    void delete(List<Integer> ids);

    @Insert("insert into student (name, no, gender, phone, id_card, is_college, address, degree, graduation_date, clazz_id,update_time,create_time) VALUES (#{name},#{no},#{gender},#{phone},#{idCard},#{isCollege},#{address},#{degree},#{graduationDate},#{clazzId},#{updateTime},#{createTime})")
    void insert(Student student);

    void update(Student student);

    void updateViolation(Integer id, Integer score);

    /**
     * 删除班级前，把该班级下的学生移到“暂无对应班级”
     */
    @Update("update student set clazz_id = 0, update_time = now() where clazz_id = #{clazzId}")
    void clearClazzId(Integer clazzId);

    @MapKey("name")
    List<Map<String, Object>> countDegreeData();

    @MapKey("name")
    List<Map<String, Object>> getStudentCountData();
}
