package com.campusflow.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.ognl.Ognl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.campusflow.pojo.ClazzCountOption;
import com.campusflow.pojo.JobOption;
import com.campusflow.pojo.Result;
import com.campusflow.service.ReportService;
import com.campusflow.service.StudentService;

import java.util.List;
import java.util.Map;


@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private StudentService studentService;


    /**
     * 统计员工职位人数
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("统计员工职位人数");
         JobOption jobOption=reportService.getEmpJobData();
         return Result.success((jobOption));
    }

    /**
     * 统计员工性别
     */
    @GetMapping("/empGenderData")
    public Result getGenderData(){
        log.info("统计员工性别人数");
        List<Map<String,Object>> list = reportService.getGenderData();
        return Result.success(list);
    }

    /**
     * 统计学生学历
     */
    @GetMapping("/studentDegreeData")
    public Result getDegreeData(){
        log.info("统计学生学历");
        List<Map<String, Object>> list = reportService.getDegreeData();
        return Result.success(list);
    }

    /**
     * 统计班级班级人数
     */
    @GetMapping("/studentCountData")
    public Result getStudentCountData(){
        log.info("统计班级人数");
        ClazzCountOption clazzCountOption = reportService.getStudentCountData();
        return Result.success(clazzCountOption);
    }

}
