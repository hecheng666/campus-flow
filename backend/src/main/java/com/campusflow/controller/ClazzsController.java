package com.campusflow.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.campusflow.pojo.Clazz;
import com.campusflow.pojo.PageResult;
import com.campusflow.pojo.Result;
import com.campusflow.service.ClazzsService;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/clazzs")
@Slf4j
public class ClazzsController {
    @Autowired
    ClazzsService clazzsService;
    
    @GetMapping("/list")
    public Result findAll(){
        log.info("查询所有班级");
        List<Clazz> list=clazzsService.findAll();
        return Result.success(list);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除班级：{}",id);
        clazzsService.delete(id);
        return Result.success();
    }

    @GetMapping
    public Result page(@RequestParam(value = "page",defaultValue = "1") Integer page,
                       @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                       String name,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end
                       )
    {
        log.info("分页查询班级：page={}, pageSize={}, name={}, begin={}, end={}", page, pageSize, name, begin, end);
        PageResult pageResult = clazzsService.page(page,pageSize,name,begin,end);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result add(@Valid @RequestBody Clazz clazz){
        log.info("添加班级：{}",clazz);
        clazzsService.add(clazz);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
        log.info("根据ID查询班级：{}",id);
        Clazz clazz = clazzsService.findById(id);
        return Result.success(clazz);
    }

    @PutMapping
    public Result save(@RequestBody Clazz clazz){
        log.info("修改员工：{}",clazz);
        clazzsService.save(clazz);
        return Result.success();
    }

}