package com.campusflow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.campusflow.mapper.EmpMapper;
import com.campusflow.mapper.StudentMapper;
import com.campusflow.pojo.ClazzCountOption;
import com.campusflow.pojo.JobOption;
import com.campusflow.pojo.Student;
import com.campusflow.service.ReportService;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private StudentMapper studentMapper;


    //统计员工职位人数
    @Override
    public JobOption getEmpJobData() {
        //统计员工职位人数
        List<Map<String, Object>> list = empMapper.countEmpJobData();   //map:  pos=系主任:num=1


        //封装JobOption
        List<Object> pos = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> num = list.stream().map(dataMap -> dataMap.get("num")).toList();

        return new JobOption(pos,num);


    }

    //统计员工性别人数
    @Override
    public List<Map<String, Object>> getGenderData() {
        return empMapper.countGenderData();
    }


    //统计学生学历人数
    @Override
    public List<Map<String, Object>> getDegreeData() {
        return studentMapper.countDegreeData();
    }


    //统计班级人数
    @Override
    public ClazzCountOption getStudentCountData() {
        List<Map<String,Object>> list = studentMapper.getStudentCountData();
        List<Object> name = list.stream().map(dataMap -> dataMap.get("name")).toList();
        List<Object> value = list.stream().map(dataMap -> dataMap.get("value")).toList();
        return new ClazzCountOption(name,value) ;
    }
}
