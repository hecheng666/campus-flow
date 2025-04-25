package com.campusflow.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.campusflow.pojo.PageResult;
import com.campusflow.pojo.Result;
import com.campusflow.pojo.Student;
import com.campusflow.service.StudentService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentService studentService;
    @GetMapping
    public Result page(@RequestParam(value = "page",defaultValue = "1") Integer page,
                       @RequestParam(value = "pageSize",defaultValue =  "10")Integer pageSize,
                       String name,
                       Integer degree,
                       Integer clazzId
                       )
    {
        log.info("分页查询：page={}, pageSize={}, name={}, degree={}, clazzId={}", page, pageSize, name, degree, clazzId);
        PageResult pageResult = studentService.page(page,pageSize,name,degree,clazzId);
        return Result.success(pageResult);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据ID查询学生：{}",id);
        Student  student = studentService.findById(id);
        return Result .success(student);
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除学生：{}",ids);
        studentService.delete(ids);
        return Result.success();
    }

    @PostMapping
    public Result add(@Valid @RequestBody Student student){
        log.info("添加学生：{}",student);
        studentService.add(student);
        return Result.success();
    }

    @PutMapping
    public Result save(@RequestBody Student student){
        log.info("修改学生：{}",student);
        studentService.save(student);
        return Result.success();
    }

    @PutMapping("/violation/{id}/{score}")
    public Result violationHandle(@PathVariable Integer id,@PathVariable(value = "score") Integer score){
        log.info("违规处理：id为{},扣除{}分",id,score);
        studentService.violationHandle(id,score);
        return Result.success();
    }
}
