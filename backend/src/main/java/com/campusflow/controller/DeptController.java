package com.campusflow.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.campusflow.anno.Log;
import com.campusflow.pojo.Dept;
import com.campusflow.pojo.Result;
import com.campusflow.service.DeptService;
import jakarta.validation.Valid;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController//
public class DeptController {
    @Autowired
    private DeptService deptService;


    /**
     * 查询部门
     * @return
     */
    @GetMapping
    public Result list(){
      log.info("查询全部部门");
      List<Dept> list=deptService.findAll();
      return Result.success(list);
    }

    /**
     * 根据ID删除部门
     * @param deptId 部门ID
     * @return
     */
    @DeleteMapping
    @Log
    public Result deleteById(@RequestParam("id") Integer deptId){
        log.info("根据ID删除部门为：{}",deptId);
        deptService.deleteById(deptId);
        return Result.success();
    }

    /**
     * 添加部门
     * @param dept 部门对象
     * @return
     */
    @PostMapping
    @Log
    public Result add(@Valid @RequestBody Dept dept){
        log.info("添加部门：{}",dept.getName());
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 根据ID查询部门
     * @param id 部门ID
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable("id") Integer id){
        log.info("根据ID查询部门：{}",id);
        Dept dept = deptService.findById(id);
        return Result.success(dept);
    }

    /**
     * 修改部门
     * @param dept 部门对象
     * @return
     */
    @PutMapping
    @Log
    public Result update(@RequestBody Dept dept){
        log.info("修改的部门ID为：{}",dept.getId());
        deptService.update(dept);
        return Result.success();
    }
}
