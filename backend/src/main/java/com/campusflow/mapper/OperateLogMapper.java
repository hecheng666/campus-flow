package com.campusflow.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.campusflow.pojo.OperateLog;


import java.util.List;

@Mapper
public interface OperateLogMapper {

    //插入日志数据
    @Insert("insert into operate_log (operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time) " +
            "values (#{operateEmpId}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime});")
    public void insert(OperateLog log);

    //查询总数
    @Select("select count(*) from operate_log;")
    public Long getCount();

    //分页查询
    @Select("select l.* ,e.name operateEmpName from operate_log l left join emp e on l.operate_emp_id = e.id order by l.operate_time limit #{start},#{pageSize};")
    public List<OperateLog> pageList(Integer start, Integer pageSize);
}
